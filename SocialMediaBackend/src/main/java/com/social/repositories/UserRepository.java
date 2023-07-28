package com.social.repositories;

import com.social.pojo.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author DinhChuong
 */
public interface UserRepository {
    
    List<User> getUsers(Map<String, String> params);

    User getUserByUsername(String username);

    Optional<User> getUserByAlumniId(String alumniId);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserBySlug(String slug);
    
    User saveOrUpdateUser(User user); 
    
    boolean deleteUser(Integer id);
}
