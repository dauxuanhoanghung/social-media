/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories;

import com.social.pojo.Community;
import com.social.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DinhChuong
 */
public interface CommunityRepository {

    List<Community> getCommunities(Map<String, String> params);
    
    Community createCommunity(Community community);

    Community addUser(int communityId, List<User> users);

    Community removeUser(int communityId, List<User> users);
    
    boolean deleteCommunity(int communityId);

    Community toggleActiveCommunity(int communityId, boolean status);
}
