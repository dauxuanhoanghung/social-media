package com.social.controllers;

import com.social.dto.request.SurveyRequest;
import com.social.enums.QuestionType;
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
@RequestMapping("/admin/post")
public class PostController {
    
    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("questionTypes", QuestionType.values());
        return "create-post";
    }
    
    @PostMapping
    public String createForm(@ModelAttribute SurveyRequest surveyRequest) {
        return "redirect:/";
    }
}
