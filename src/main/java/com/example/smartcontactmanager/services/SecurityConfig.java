//package com.example.smartcontactmanager.services;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecurityConfig {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailService;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(authorize -> {
//            authorize.requestMatchers("/user/**").authenticated();
//            authorize.anyRequest().permitAll();
//        });
//
//
//
//        return httpSecurity.build();
//
//    }
//
//}
//
