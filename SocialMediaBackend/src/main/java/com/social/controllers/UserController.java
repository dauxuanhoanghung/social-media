/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.controllers;

import com.social.dto.request.UserRegisterDTO;
import com.social.enums.UserStatus;
import com.social.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void commonAttributesUser(Model model) {
        model.addAttribute("roles", this.userService.getAllRoles());
        model.addAttribute("status", UserStatus.values());
    }

    @GetMapping
    public String get() {
        return "user";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "create-user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute(value = "user") UserRegisterDTO user) {
        user.setStatus(UserStatus.valueOf(user.getStatus().toString()));
        System.out.println("com.social.controllers.UserController.createUser(): " + user);
        this.userService.saveOrUpdateUser(user);
        return "redirect:/admin/user";
    }
}
