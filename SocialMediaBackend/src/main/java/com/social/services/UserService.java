package com.social.services;

import com.social.dto.UserDTO;
import com.social.dto.request.UserRegisterDTO;
import com.social.pojo.Role;
import com.social.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author DinhChuong
 */
public interface UserService extends UserDetailsService {

    List<Role> getAllRoles();

    User getUserByUsername(String username);

    User getUserByAlumniId(String alumniId);

    User getUserByEmail(String email);

    User getUserBySlug(String slug);

    User saveOrUpdateUser(UserDTO user);

    User saveOrUpdateUser(UserRegisterDTO user);

    boolean deleteUser(Integer id);
}
