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
import com.jeanhefler.library.infra.security.TokenService;
import com.jeanhefler.library.mapper.UserMapper;
import com.jeanhefler.library.model.User;
import com.jeanhefler.library.service.UserService;

@RestController
@RequestMapping("/auth")
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
    public ResponseEntity<String> login(@RequestBody AuthenticationDto data){
        var emailAndPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(emailAndPassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok().body("token: " + token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto data){
        if (this.userService.findUserByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encriptedPassword, data.role());
        this.userService.createNewUser(newUser);

        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<User> users = userService.findAllUsers();
        List<UserDto> usersDto = users.stream()
            .map(UserMapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }
}
