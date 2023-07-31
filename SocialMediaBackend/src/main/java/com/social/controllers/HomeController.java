package com.social.controllers;

import com.social.enums.QuestionType;
import com.social.services.MailService;
import com.social.services.StatsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Controller
public class HomeController {

    @Autowired
    private MailService mailService;
    
    @Autowired
    private StatsService statsService;
    
    @GetMapping("/")
    public String home(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("userStats", this.statsService.countUsers(null));
        return "index";
    }

    @GetMapping("/account")
    public String account() {
        return "account-settings";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/enum")
    public String enums(Model model) {
        model.addAttribute("questionType", QuestionType.values());
        return "index";
    }

}
