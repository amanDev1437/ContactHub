package com.example.smartcontactmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String subject, String body, String to){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom("contacthub@demomailtrap.com");

        try{
            javaMailSender.send(mailMessage);
            return true;
        }catch (MailException exception){
            exception.printStackTrace();
            return false;
        }


    }

}
