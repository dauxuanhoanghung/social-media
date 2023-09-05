package com.social.services;

import com.social.dto.request.UserRegisterDTO;
import com.social.pojo.Role;
import com.social.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DinhChuong
 */
public interface UserService extends UserDetailsService {

    List<Role> getAllRoles();

    List<User> getUsers(Map<String, String> params);

    User getUserById(int id);

    User getUserByUsername(String AlumniId);

    User getUserByAlumniId(String alumniId);

    User getUserByEmail(String email);

    User getUserBySlug(String slug);

    User saveOrUpdateUser(User user);
    
    User updateInfo(User user);
    
    User update(User user);
    
    User updateStatus(User user);
    
    User updateAvatar(MultipartFile avatarFile);

    boolean deleteUser(Integer id);
    
    User save(UserRegisterDTO user); // Save new record
    
}
