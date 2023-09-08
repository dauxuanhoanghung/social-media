/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.dto.request;

import com.social.pojo.User;
import java.util.List;
import lombok.Data;

/**
 *
 * @author DinhChuong
 */
@Data
public class CommunityRequest {
    private List<User> users;
    private int communityId;
}
