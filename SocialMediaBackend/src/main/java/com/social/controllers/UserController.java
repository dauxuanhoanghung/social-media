/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.controllers;

import com.social.dto.request.UserRegisterDTO;
import com.social.enums.UserStatus;
import com.social.pojo.User;
import com.social.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String get(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", userService.getUsers(params));
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
        this.userService.saveOrUpdateUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/active")
    public String alumni(Model model, @RequestParam Map<String, String> params) {
        params.put("roleId", "2");
        model.addAttribute("users", this.userService.getUsers(params));
        return "user";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable(value = "id") int id,
            Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "account";
    }

    @PutMapping("/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void change(@PathVariable(value = "id") int id,
            @PathVariable(value = "status") String status,
            RedirectAttributes redirectAttributes) {
        User user = this.userService.getUserById(id);
        user.setStatus(UserStatus.valueOf(status));
        this.userService.saveOrUpdateUser(user);
        System.out.println(status);
//        redirectAttributes.addFlashAttribute("status", UserStatus.values());
//        return "redirect:/admin/user/{id}";
    }

}
