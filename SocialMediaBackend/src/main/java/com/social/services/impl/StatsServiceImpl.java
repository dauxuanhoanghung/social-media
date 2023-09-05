package com.social.services.impl;

import com.social.repositories.StatsRepository;
import com.social.services.StatsService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Lazy
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<Object[]> countUsers(Map<String, String> params) {
        return this.statsRepository.countNewUsers(params);
    }

    @Override
    public List<Object[]> countNewUsers(LocalDateTime fromDate, LocalDateTime toDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> countPosts(Map<String, String> params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> countPosts(LocalDateTime fromDate, LocalDateTime toDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object[]> getTop10MostActiveUser(Map<String, String> params) {
        return statsRepository.top10MostActiveUser(params);
    }
}
