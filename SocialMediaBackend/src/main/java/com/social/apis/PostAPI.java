/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.apis;

import com.social.pojo.User;
import com.social.servicces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/post")
public class PostAPI {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String getAllPost(){
        User user = userService.getUserByUsername("dhthanh");
        return user.getPassword();
    }
    
    @PostMapping
    public void createPost(String text, String username){
       
    }
}
