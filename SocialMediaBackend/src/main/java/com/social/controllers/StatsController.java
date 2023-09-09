package com.social.controllers;

import com.social.services.StatsService;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String statisticUser(Map<String, String> params, Model model) {
        List top10User = statsService.getTop10MostActiveUser(params);
        model.addAttribute("listTop10", top10User);
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "most-active-user";
    }
    
    @GetMapping("/posts")
    public String statisticPost(Map<String, String> params, Model model) {
        model.addAttribute("posts", statsService.countPosts(params));
        return "statistic-post";
    }
    
    @GetMapping("/user-register/")
    @ResponseBody
    public ResponseEntity getUserRegisterByConditions(@RequestParam Map<String, String> params) {
        return new ResponseEntity(statsService.countUsers(params), HttpStatus.OK);
    }
    
    @GetMapping("/lastest-register/")
    @ResponseBody
    public ResponseEntity getNumberOfUsersInLastestMonth() {
        return new ResponseEntity(statsService.getNumberOfUsersInLastestMonth(3), HttpStatus.OK);
    }
}
