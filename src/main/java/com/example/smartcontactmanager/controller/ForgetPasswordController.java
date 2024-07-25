package com.example.smartcontactmanager.controller;

import com.example.smartcontactmanager.model.User;
import com.example.smartcontactmanager.services.EmailService;
import com.example.smartcontactmanager.services.Message;
import com.example.smartcontactmanager.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    Random random = new Random(100000);

    @GetMapping("/reset")
    public String gotoForgetPassword(){

        return "forgetPassword";

    }

    @PostMapping("/reset")
    public String forgetPassword(@RequestParam String email, HttpSession session){

        if(userService.getUser(email)==null){

            session.setAttribute("message",new Message("This email is not registered","alert-danger"));

            return "redirect:/forgetPassword/reset";
        }

        int otp = random.nextInt(999999);

        System.out.println(otp);

        String subject = "OTP from ContactHub";
        String body = "Your otp to reset your password:"+otp;

        if(emailService.sendEmail(subject,body,email)){

            session.setAttribute("sentOTP",otp);
            session.setAttribute("email",email);
            session.setAttribute("message",new Message("OTP sent on email !!","alert-success"));

            return "verifyOtp";
        }else{
            session.setAttribute("message",new Message("Something went wrong !!","alert-danger"));
            return "redirect:/forgetPassword/reset";
        }

    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("otp") Integer enterOTP,HttpSession session){

        Integer sentOTP = (Integer) session.getAttribute("sentOTP");


        if(Objects.equals(sentOTP, enterOTP)){
            return "newPassword";
        }else {
            session.setAttribute("message",new Message("Invalid OTP","alert-danger"));
            return "verifyOtp";
        }

    }

    @PostMapping("/newPassword")
    public String setNewPassword(@RequestParam String newPassword,HttpSession session){

        String email = (String) session.getAttribute("email");

        User user  = userService.getUser(email);

        user.setPassword(newPassword);

        userService.saveUser(user);

        session.setAttribute("message",new Message("Password changed successfully","alert-success"));

        return "redirect:/login";

    }
}
