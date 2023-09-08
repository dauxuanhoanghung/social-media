package com.social.services;

import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface StatsService {

    List<Object[]> countUsers(Map<String, String> params);

    List<Object[]> countPosts(Map<String, String> params);
    
    List<Object[]> getTop10MostActiveUser(Map<String, String> params);
    
    List<Object[]> getNumberOfUsersInLastestMonth(int months);
}
