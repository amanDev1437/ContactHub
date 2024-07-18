package com.example.smartcontactmanager.services;

import com.example.smartcontactmanager.model.UserPrincipal;
import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException("Could not found user");
        }

        return new UserPrincipal(user);

    }
}
