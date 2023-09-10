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

    Optional<User> getUserByAlumniId(String alumniId);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserBySlug(String slug);

    User save(User user);
    
    User update(User user);
    
    boolean deleteUser(Integer id);
    
    boolean existsByAlumniId(String alumniId);
    
    int updateAvatarOrBg(String alumniId,String url, boolean isBg);
    
    Long count();
}
