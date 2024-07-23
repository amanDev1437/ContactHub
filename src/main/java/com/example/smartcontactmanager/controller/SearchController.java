package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.model.Contact;
import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.services.ContactService;
import com.example.smartcontactmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal){

        User user = userService.getUser(principal.getName());

        List<Contact> searchList = contactService.searchContact(query,user);

        System.out.println(query);
        System.out.println(searchList);

        return ResponseEntity.ok(searchList);

    }
}
