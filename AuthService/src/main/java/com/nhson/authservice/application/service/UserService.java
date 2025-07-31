package com.nhson.authservice.application.service;

import com.nhson.authservice.application.dto.RegisterReq;
import com.nhson.authservice.application.model.Role;
import com.nhson.authservice.application.model.User;
import com.nhson.authservice.application.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public void createAccount(RegisterReq req){
        if (userRepository.existsByUsername(req.username())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User(null,req.username(),passwordEncoder.encode(req.password()), Role.USER);
        userRepository.save(user);
    }
}
