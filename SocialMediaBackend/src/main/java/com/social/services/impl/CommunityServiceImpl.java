/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.services.impl;

import com.social.pojo.Community;
import com.social.pojo.User;
import com.social.repositories.CommunityRepository;
import com.social.services.CommunityService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CommunityRepository communityRepository;
    
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Community createCommunity(Community community) {
        return communityRepository.createCommunity(community);
    }

    @Override
    public Community removeUser(int communityId, List<User> users) {
        return communityRepository.removeUser(communityId, users);
    }

   

}
