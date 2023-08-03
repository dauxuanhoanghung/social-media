package com.social.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */
@Controller
@RequestMapping("/admin/post")
public class PostController {
    
    @GetMapping
    public String getForm(Model model) {
        
        return "create-post";
    }
}
