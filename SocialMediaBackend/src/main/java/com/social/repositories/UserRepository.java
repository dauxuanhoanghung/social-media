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

    Optional<User> getUserById(int id);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByAlumniId(String alumniId);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserBySlug(String slug);

    User save(User user);
    
    User update(User user);

    boolean deleteUser(Integer id);
}
