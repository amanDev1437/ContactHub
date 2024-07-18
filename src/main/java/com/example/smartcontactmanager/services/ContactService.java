package com.example.smartcontactmanager.services;

import com.example.smartcontactmanager.model.Contact;
import com.example.smartcontactmanager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact){

        return contactRepository.save(contact);
    }
}
