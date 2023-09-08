/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.controllers;

import com.social.dto.request.CommunityRequest;
import com.social.pojo.Community;
import com.social.pojo.User;
import com.social.services.CommunityService;
import com.social.services.UserService;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    @GetMapping("/group-update")
    public String groupEdit(Model model, @RequestParam Map<String, String> params) {
        List<Community> groups = communityService.getCommunities(params);
        Community myGroup = groups.get(0);
        Set<User> userOfGroup = myGroup.getUsers();
        model.addAttribute("userOfGroup", userOfGroup);
        model.addAttribute("users", userService.getUsers(params));
        model.addAttribute("myGroup", myGroup);
        return "group-update";
    }

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
        return "group";
    }

    @PostMapping("/toggle-active")
    public String toggleActive(@RequestBody Map<String, String> params) {
        int communityId = Integer.parseInt(params.get("communityId"));
        boolean status = Boolean.parseBoolean(params.get("status"));
        communityService.toggleActiveCommunity(communityId, status);
        return "group";
    }

    @PostMapping("/update-group")
    public String updateGroup(@RequestBody Community community) {
        communityService.updateGroup(community);
        return "group";
    }
}
