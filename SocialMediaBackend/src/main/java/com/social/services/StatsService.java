package com.social.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface StatsService {

    List<Object[]> countUsers(Map<String, String> params);

    List<Object[]> countNewUsers(LocalDateTime fromDate, LocalDateTime toDate);

    List<Object[]> countPosts(Map<String, String> params);

    List<Object[]> countPosts(LocalDateTime fromDate, LocalDateTime toDate);
    
    List<Object[]> getTop10MostActiveUser(Map<String, String> params);
}
