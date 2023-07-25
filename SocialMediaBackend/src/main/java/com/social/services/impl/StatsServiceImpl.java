package com.social.services.impl;

import com.social.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class StatsServiceImpl {
    @Autowired
    private StatsRepository statsRepository;
}
