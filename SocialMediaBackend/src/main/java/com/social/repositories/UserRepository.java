/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories;

import com.social.pojo.User;
import java.util.Optional;

/**
 *
 * @author DinhChuong
 */
public interface UserRepository {

    User getUserByUsername(String username);

    Optional<User> getUserByAlumniId(String alumniId);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserBySlug(String slug);
    
    User saveOrUpdateUser(User user); 
    
    boolean deleteUser(Integer id);
}
