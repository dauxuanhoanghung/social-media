/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.controllers;

import com.social.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author LENOVO
 */
@Controller
public class HomeController {
    
    @Autowired
    private MailService mailService;

    @GetMapping("/")
    public String home() {
//        mailService.sendMail("nhphuc414@gmail.com", "HELOO", "1111", "invitation");
        return "home";
    }

    @GetMapping("/account")
    public String account() {
        return "account-settings";
    }
    
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
}
