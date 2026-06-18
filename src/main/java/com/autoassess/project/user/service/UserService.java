package com.autoassess.project.user.service;

import com.autoassess.project.security.JwtUtil;
import com.autoassess.project.user.dto.LoginRequest;
import com.autoassess.project.user.dto.RegisterRequest;
import com.autoassess.project.user.dto.RegisterResponse;
import com.autoassess.project.user.entity.User;
import com.autoassess.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger log=LoggerFactory.getLogger(UserService.class);

    public RegisterResponse register(RegisterRequest request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered");
        }
        log.info("Starting registration for email: {}", request.getEmail());

        User user=new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
        user.setPassword(encode.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        log.info("User registered successfully. User ID: {}", savedUser.getId());

        RegisterResponse response= new RegisterResponse();
        response.setUserId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setMessage("User registered successfully");

        return response;
    }

    public String login(LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        log.info("Login successful. User ID: {}", user.getId());

        return jwtUtil.generateToken(user.getEmail());
    }

}
