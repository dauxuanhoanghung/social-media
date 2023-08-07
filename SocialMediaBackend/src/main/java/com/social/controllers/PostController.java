package com.social.controllers;

import com.social.dto.request.SurveyRequest;
import com.social.enums.QuestionType;
import com.social.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */
@Controller
@RequestMapping("/admin/post")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("questionTypes", QuestionType.values());
        return "create-post";
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createForm(@RequestBody SurveyRequest surveyRequest) {
        postService.saveSurveyPost(surveyRequest);
        return "redirect:/";
    }
}
