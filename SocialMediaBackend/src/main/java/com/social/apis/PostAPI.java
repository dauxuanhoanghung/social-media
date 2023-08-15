/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.apis;

import com.social.dto.request.PostRequest;
import com.social.exceptions.NotFoundException;
import com.social.services.PostService;
import com.social.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private PostService postService;
    
    @GetMapping
    public String getAllPost() {
        return "authen success";
    }

    @PostMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createPost(@ModelAttribute PostRequest post) {
        postService.save(post);
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
