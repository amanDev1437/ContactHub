package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.model.Contact;
import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.services.ContactService;
import com.example.smartcontactmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @ModelAttribute
    public void addCommonData(Model model, Principal principal){

        User user = userService.getUser(principal.getName());

        model.addAttribute("user",user);

    }

    @RequestMapping("/index")
    public String dashboard(Model model){


        model.addAttribute("title","Dashboard");


        return "userDashboard";
    }

    @GetMapping("/addContact")
    public String gotoAddContact(Model model){

        model.addAttribute("title","Add Contact");
        model.addAttribute("contact",new Contact());

        return "addContact";
    }

    @PostMapping("/addContact")
    public String addContact(@ModelAttribute Contact contact){

        contactService.saveContact(contact);
        System.out.println(contact);
        return "redirect:/user/index";

    }

}
