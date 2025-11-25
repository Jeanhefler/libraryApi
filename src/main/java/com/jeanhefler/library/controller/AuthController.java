package com.jeanhefler.library.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhefler.library.dto.AuthenticationDto;
import com.jeanhefler.library.dto.RegisterDto;
import com.jeanhefler.library.dto.UserDto;
import com.jeanhefler.library.infra.security.SecurityConfiguration;
import com.jeanhefler.library.infra.security.TokenService;
import com.jeanhefler.library.mapper.UserMapper;
import com.jeanhefler.library.model.User;
import com.jeanhefler.library.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "User", description = "Register, authenticate and list users")
public class AuthController {
    
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private TokenService tokenService;
    
    public AuthController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @Operation(summary = "User authentication", description = "Authenticate with a registered user")
    @ApiResponse(responseCode = "200", description = "User authenticated")
    @ApiResponse(responseCode = "403", description = "Invalid credentials")
    public ResponseEntity<String> login(@RequestBody AuthenticationDto data){
        var emailAndPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(emailAndPassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok().body("token: " + token);
    }

    @PostMapping("/register")
    @Operation(summary = "User register", description = "Create a new user; name lenght should be more than 3 characters, E-mail should be valid, password lenght should be more than 8 characters")
    @ApiResponse(responseCode = "201", description = "User created")
    @ApiResponse(responseCode = "400", description = "E-mail is already registered")
    @ApiResponse(responseCode = "403", description = "Bad response values")
    public ResponseEntity<User> register(@RequestBody RegisterDto data){
        if (this.userService.findUserByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encriptedPassword, data.role());
        this.userService.createNewUser(newUser);

        return ResponseEntity.status(201).build();
    }
    
    @GetMapping("/users")
    @Operation(summary = "List users", description = "List all registered users (admin)")
    @ApiResponse(responseCode = "200", description = "Request successful")
    @ApiResponse(responseCode = "403", description = "Unauthorized")
    @SecurityRequirement(name = SecurityConfiguration.SECURITY)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users = userService.findAllUsers();
        List<UserDto> usersDto = users.stream()
            .map(UserMapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }
}
