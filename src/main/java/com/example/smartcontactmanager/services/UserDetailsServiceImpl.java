//package com.example.smartcontactmanager.services;
//
//import com.example.smartcontactmanager.entities.User;
//import com.example.smartcontactmanager.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.getUserByEmail(username);
//
//        if(user==null){
//            throw new UsernameNotFoundException("Could not found user");
//        }
//
//        return new CustomUserDetails(user);
//
//    }
//}
