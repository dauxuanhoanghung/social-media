package com.social.controllers;

import com.social.services.StatsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */
@Lazy
@Controller
@RequestMapping("/admin/statistic")
public class StatsController {

    @Autowired
    private StatsService statsService;
    
    @GetMapping("/users")
    public String getTop10MostActiveUser(Map<String, String> params, Model model) {
        List top10User = statsService.getTop10MostActiveUser(params);
        model.addAttribute("listTop10", top10User);
        return "most-active-user";
    }
}
