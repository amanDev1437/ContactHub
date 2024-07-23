package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.model.Contact;
import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.services.ContactService;
import com.example.smartcontactmanager.services.Message;
import com.example.smartcontactmanager.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public String addContact(@ModelAttribute Contact contact, Principal principal, HttpSession session,Model model){

        try{
            contactService.saveContact(contact,principal);
            session.setAttribute("message",new Message("Contact is added!!","alert-success"));
        }catch (Exception e){
            System.out.println("ERROR"+e.getMessage());
            e.printStackTrace();
            session.setAttribute("message",new Message("something went wrong !!","alert-danger"));
        }

        return "redirect:/user/addContact";
    }

    @GetMapping("/viewContact/{page}")
    public String gotoViewContact(@PathVariable("page") Integer page, Model model, Principal principal){
        model.addAttribute("title","View Contact");

        User user = userService.getUser(principal.getName());
        Page<Contact> contactList = contactService.getAllContact(user,page);

        model.addAttribute("contactList",contactList);
        model.addAttribute("currentPage",page);

        model.addAttribute("totalPage",contactList.getTotalPages());

        return "viewContact";

    }

    @GetMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable int contactId, HttpSession session, HttpServletRequest request){

        contactService.deleteContact(contactId);

        session.setAttribute("message",new Message("contact deleted successfully","alert-success"));

        String url = request.getHeader("Referer");

        session.setAttribute("url",url);

        return "redirect:/user/viewContact/0";

    }

    @GetMapping("/update/{contactId}")
    public String updateContact(@PathVariable int contactId,Model model, HttpSession session){

        model.addAttribute("title","update");

        model.addAttribute("contact",contactService.getContactById(contactId));


        return "updateContact";
    }

    @PostMapping("/updateContact")
    public String updateContact(@ModelAttribute Contact contact,HttpSession session,Principal principal){


        contactService.saveContact(contact,principal);

        session.setAttribute("message",new Message("Contact updated successfully","alert-success"));


        return "redirect:/user/viewContact/0";
    }

    @GetMapping("/profile")
    public String gotoProfile(Model model, Principal principal){

        User user = userService.getUser(principal.getName());

        int totalContact = contactService.countContact(user);

        model.addAttribute("title","Profile");

        model.addAttribute("totalContact",totalContact);

        return "profile";
    }



}
