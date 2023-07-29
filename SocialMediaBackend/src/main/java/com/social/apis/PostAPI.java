/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.apis;

import com.social.exceptions.NotFoundException;
import com.social.services.UserService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/post/")
public class PostAPI {

    @Autowired
    private UserService userService;
    
    @Resource
    private transient MessageSource messageSource;

    @GetMapping
    public String getAllPost() {
        return "authen success";
    }

    @PostMapping
    public void createPost(String text, String username, WebRequest request) {
        
    }
    
    @GetMapping("/err")
    public void err(WebRequest request){
        try {
            throw new NotFoundException("User not found !! OKKK", "1", request.getDescription(false));
        } catch(Exception ex) {
            throw new NotFoundException("User not found!!", "1", request.getDescription(false));
        }
    }
}
