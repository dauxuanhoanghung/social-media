/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.controllers;

import com.social.dto.request.CommunityRequest;
import com.social.pojo.Community;
import com.social.services.CommunityService;
import com.social.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Controller
@RequestMapping("/admin/community")
public class GroupController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommunityService communityService;

    @GetMapping("/group")
    public String group(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", userService.getUsers(params));
        List<Community> groups = communityService.getCommunities(params);
        model.addAttribute("groups", groups);
        return "group";
    }
    // Test Community

    @PostMapping("/create-group")
    public String createCommunity(@RequestBody Community community) {
        communityService.createCommunity(community);
        return "redirect:/admin/community/group";
    }

    @PostMapping("/add-user/")
    public Community addUserCommunity(@RequestBody CommunityRequest request) {
        return communityService.addUser(request.getCommunityId(), request.getUsers());
    }

    @DeleteMapping("/delete-user/")
    public Community deleteUserCommunity(@RequestBody CommunityRequest request) {
        return communityService.removeUser(request.getCommunityId(), request.getUsers());
    }

    @DeleteMapping("/delete-community/{id}")
    public String deleteCommunity(@PathVariable int id) {
        boolean isDeleted = communityService.deleteCommunity(id);
        return isDeleted == true ? "Xoá thànnh công" : "Xoá thất bại";
    }
}
