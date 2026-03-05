package com.nutritonix.authservice.service;


import com.nutritonix.authservice.entity.UserCredential;
import com.nutritonix.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public UserCredential saveUser(UserCredential credential) {
        if(repository.findByEmail(credential.getEmail()).isPresent())
        {
            throw new RuntimeException("User already exist with this email");
        }
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
         return repository.save(credential);

    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public UserCredential findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}