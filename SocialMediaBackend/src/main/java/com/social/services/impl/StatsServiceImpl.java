package com.social.services.impl;

import com.social.repositories.StatsRepository;
import com.social.services.StatsService;
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
    public List<Object[]> countPosts(Map<String, String> params) {
        return this.statsRepository.countPosts(params);
    }

    @Override
    public List<Object[]> getTop10MostActiveUser(Map<String, String> params) {
        return statsRepository.top10MostActiveUser(params);
    }

    @Override
    public List<Object[]> getNumberOfUsersInLastestMonth(int months) {
        return this.statsRepository.getNumberOfUsersInLastestMonth(months);
    }
}
