package com.social.controllers;

import com.social.dto.request.SurveyRequest;
import com.social.enums.QuestionType;
import com.social.enums.UserStatus;
import com.social.services.PostService;
import com.social.validator.SurveyRequestValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    private SurveyRequestValidator surveyRequestValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(surveyRequestValidator);
    }

    @ModelAttribute
    public void commonAttributesUser(Model model) {
        model.addAttribute("questionTypes", QuestionType.values());
    }

    @GetMapping("/create")
    public String getForm(Model model) {
        return "create-post";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createForm(@RequestBody @Valid SurveyRequest surveyRequest, BindingResult rs) {
        if (rs.hasErrors()) {
            return "create-post";
        }
        postService.saveSurveyPost(surveyRequest);
        return "redirect:/";
    }
}
