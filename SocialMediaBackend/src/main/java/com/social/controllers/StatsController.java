package com.social.controllers;

import com.social.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author LENOVO
 */
@Controller
public class StatsController {
    @Autowired
    private StatsService statsService;
    
    
}
