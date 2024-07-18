package com.example.smartcontactmanager.services;

import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user){

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    public User getUser(String email){

        return userRepository.findByEmail(email);
    }
}
