package com.social.controllers;

import com.social.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */
@Controller
@RequestMapping("/admin/statistic")
public class StatsController {
    @Autowired
    private StatsService statsService;
    
    
}
