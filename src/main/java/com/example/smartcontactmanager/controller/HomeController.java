package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.entities.User;
import com.example.smartcontactmanager.repository.UserRepository;
import com.example.smartcontactmanager.services.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("message")
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String gotoHome(Model model) {
        model.addAttribute("title", "Home");
        return "home";
    }

    @RequestMapping(value = "/signup")
    public String showSignup(Model model, HttpSession session) {
        model.addAttribute("title", "Register");
        model.addAttribute("user", new User());
        addSessionMessageToModel(session, model);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result, Model model, HttpSession session ) {
        try {
            if(result.hasErrors()){
                model.addAttribute("user",user);
                return "signup";
            }
            userRepository.save(user);
            session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong: " + e.getMessage(), "alert-danger"));
        }
        addSessionMessageToModel(session, model);
        return "signup";
    }

    @RequestMapping("/login")
    public String gotoLogin(Model model){
        model.addAttribute("title","Login");
        return "login";
    }

    private void addSessionMessageToModel(HttpSession session, Model model) {
        if (session.getAttribute("message") != null) {
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
    }
}
