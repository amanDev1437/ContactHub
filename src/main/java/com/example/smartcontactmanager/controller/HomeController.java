package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.services.EmailService;
import com.example.smartcontactmanager.services.Message;
import com.example.smartcontactmanager.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public String redirectToHome(){

        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String gotoHome(Model model) {
        model.addAttribute("title", "Home");
        return "home";
    }

    @RequestMapping(value = "/signup")
    public String showSignup(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result, Model model, HttpSession session ) {

        String subject = "Welcome To ContactHub";
        String body = "Dear "+user.getName();
        body+= "\nWelcome to ContactHub, Your login credentials are: username= "+user.getEmail()+" and password= "+user.getPassword();
        try {
            if(result.hasErrors()){
                model.addAttribute("user",user);
                return "signup";
            }
            emailService.sendEmail(subject,body,user.getEmail());
            userService.saveUser(user);
            session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong!!", "alert-danger"));
        }
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String gotoLogin(Model model){
        model.addAttribute("title","Login");
        return "login";
    }

}
