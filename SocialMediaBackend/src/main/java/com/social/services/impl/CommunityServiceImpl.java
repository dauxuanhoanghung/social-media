/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.services.impl;

import com.social.pojo.Community;
import com.social.pojo.User;
import com.social.repositories.CommunityRepository;
import com.social.repositories.UserRepository;
import com.social.services.CommunityService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DinhChuong
 */
@Service
@Transactional
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }

    @Override
    public List<Community> getCommunities(Map<String, String> params) {
        return communityRepository.getCommunities(params);
    }

    @Override
    public Community addUser(int communityId, List<User> users) {
        return communityRepository.addUser(communityId, users);
    }

    @Override
    public boolean deleteCommunity(int communityId) {
        return communityRepository.deleteCommunity(communityId);
    }

    @Override
    public Community toggleActiveCommunity(int communityId, boolean status) {
        return communityRepository.toggleActiveCommunity(communityId, status);
    }

    @Override
    public Community createCommunity(Community community) {
        community.setFounderId(getCurrentUser());
        return communityRepository.createCommunity(community);
    }

    @Override
    public Community removeUser(int communityId, List<User> users) {
        return communityRepository.removeUser(communityId, users);
    }

    @Override
    public Community updateGroup(Community community) {
        User currentUser = getCurrentUser();
        community.setFounderId(currentUser);
        return communityRepository.updateGroup(community);
    }

}
