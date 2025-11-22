package com.jeanhefler.library.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jeanhefler.library.exception.BadRequestException;
import com.jeanhefler.library.infra.security.EmailValidation;
import com.jeanhefler.library.model.User;
import com.jeanhefler.library.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private EmailValidation emailValidation;

    public UserService(UserRepository userRepository, EmailValidation emailValidation) {
        this.userRepository = userRepository;
        this.emailValidation = emailValidation;
    }

    public UserDetails findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(User newUser){
        if (newUser.getName().length() < 3 || newUser.getName().isEmpty()) {
            throw new BadRequestException("Name should have 3 or more characters");
        }
        if (emailValidation.isValidEmail(newUser.getEmail()) == false) {
            throw new BadRequestException("e-mail should be valid");
        }
        if (newUser.getPassword().length() < 8) {
            throw new BadRequestException("Password can't be less than 8 characters");
        }
        return userRepository.save(newUser);
    }
}
