package com.example.smartcontactmanager.services;

import com.example.smartcontactmanager.model.Contact;
import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.repository.ContactRepository;
import com.example.smartcontactmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    public void saveContact(Contact contact, Principal principal){

        String email = principal.getName();

        User user = userRepository.findByEmail(email);

        contact.setUser(user);
        user.getContactList().add(contact);

        userRepository.save(user);

    }


    public Page<Contact> getAllContact(User user, int page){

        Pageable pageable = PageRequest.of(page,4);

        return contactRepository.findContactByUserId(user.getUserId(),pageable);
    }
}
