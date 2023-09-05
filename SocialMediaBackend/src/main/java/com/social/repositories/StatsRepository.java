package com.social.repositories;

import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface StatsRepository {

    /**
     * @param params LocalDateTime fromDate, toDate
     * @return
     */
    List<Object[]> countNewUsers(Map<String, String> params);

    /**
     * @param params LocalDateTime fromDate, toDate, User user, Sort by ?
     * @return
     */
    List<Object[]> countPosts(Map<String, String> params);

    /**
     *
     * @param params LocalDateTime fromDate, toDate
     * @return User, count Post, count Action, count Comment
     */
    List<Object[]> top10MostActiveUser(Map<String, String> params);
}
