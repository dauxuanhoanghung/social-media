/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.apis;

import com.social.pojo.User;
import com.social.services.UserService;
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
          return "authen success";
    }
    
    @PostMapping
    public void createPost(String text, String username){
       
    }
}
