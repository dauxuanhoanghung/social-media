package com.social.controllers;

import com.social.enums.QuestionType;
import com.social.services.MailService;
import com.social.services.StatsService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Controller
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StatsService statsService;

    @GetMapping
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

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        return "redirect:/"; // Redirect to the admin dashboard page
    }

    @GetMapping("/enum")
    public String enums(Model model) {
        model.addAttribute("questionType", QuestionType.values());
        return "index";
    }

}
