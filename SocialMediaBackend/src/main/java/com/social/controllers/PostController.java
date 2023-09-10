package com.social.controllers;

import com.social.dto.request.SurveyRequest;
import com.social.enums.PostType;
import com.social.enums.QuestionType;
import com.social.pojo.Post;
import com.social.services.PostService;
import com.social.services.SurveyResultService;
import com.social.validator.SurveyRequestValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Lazy
@Controller
@RequestMapping("/admin/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private SurveyRequestValidator surveyRequestValidator;

    @Autowired
    private SurveyResultService surveyResultService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(surveyRequestValidator);
    }

    @ModelAttribute
    public void commonAttributesUser(Model model) {
        model.addAttribute("questionTypes", QuestionType.values());
    }

    @GetMapping
    public String index(Map<String, String> params, Model model) {
        List<Post> posts = this.postService.getPosts(params);
        model.addAttribute("posts", posts);
        return "posts";
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

    @GetMapping(value = "/result/{id}")
    public String viewSurveyResult(@PathVariable("id") int id, @RequestParam Map<String, String> params, Model model) {
        Post post = postService.getPostById(id);
        if (post.getType().equals(PostType.POST)) {
            return "redirect:/admin/post";
        }
        params.put("postId", String.valueOf(id));
        List questions = surveyResultService.getQuestionsByPostId(id);
        List<Object[]> result = surveyResultService.getResultSurvey(params);
        /**
         * {
         * "chuongdp": [ { "questionId": "5", 
         * "questionContent": "Tiền nhiều hay ít ?", 
         * "result": "Nhiều" }]
         */
        Map<String, List<Map<String, String>>> results = new HashMap<>();
        result.stream().forEach(rs -> {
            String username = String.valueOf(rs[0]);
            String questionId = String.valueOf(rs[1]);
            String questionContent = String.valueOf(rs[2]);
            String contentRs = String.valueOf(rs[3]);
            // Create a map to represent each row
            Map<String, String> rowMap = new HashMap<>();
            rowMap.put("questionId", questionId);
            rowMap.put("questionContent", questionContent);
            rowMap.put("result", contentRs);

            // Check if the username is already in the map
            if (results.containsKey(username)) {
                results.get(username).add(rowMap);
            } else {
                List<Map<String, String>> userRows = new ArrayList<>();
                userRows.add(rowMap);
                results.put(username, userRows);
            }
        });
        model.addAttribute("results", results);
        model.addAttribute("questions", questions);
        return "view-survey-result";
    }
}
