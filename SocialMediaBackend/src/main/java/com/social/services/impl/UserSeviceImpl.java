package com.social.services.impl;

import com.social.dto.UserDTO;
import com.social.dto.request.UserRegisterDTO;
import com.social.enums.UserStatus;
import com.social.exceptions.NotFoundException;
import com.social.pojo.Role;
import com.social.pojo.User;
import com.social.repositories.RoleRepository;
import com.social.repositories.UserRepository;
import com.social.services.MailService;
import com.social.services.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DinhChuong
 */
@Service
@Transactional
public class UserSeviceImpl implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        return this.userRepository.getUsers(params);
    }

    @Override
    public User getUserByUsername(String AlumniId) {
        return userRepository.getUserByAlumniId(AlumniId).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist!");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(
                user.getAlumniId(), user.getPassword(), authorities);
    }

    @Override
    public User getUserById(int id) {
        User user = this.userRepository.getUserById(id);
        return user;
    }

    @Override
    public User getUserByAlumniId(String alumniId) {
        User user = this.userRepository.getUserByAlumniId(alumniId)
                .orElseThrow(() -> new NotFoundException("User not found", alumniId, "/users/" + alumniId));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User getUserBySlug(String slug) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User saveOrUpdateUser(UserDTO user) {

        return null;
//        return this.userRepository.saveOrUpdateUser(user);
    }

    @Override
    public User saveOrUpdateUser(UserRegisterDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User entity = modelMapper.map(user, User.class);
        return this.userRepository.saveOrUpdateUser(entity);
    }

    @Override
    public boolean deleteUser(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Role> getAllRoles() {
        return this.roleRepository.getAll();
    }

    @Override
    public User saveOrUpdateUser(User user) {
        if (user.getStatus().equals(UserStatus.ACTIVE)) {
            mailService.sendMail(user.getEmail(), "Thư chấp nhận",
                    "Tài khoản của bạn đã được xác nhận", "invitation");
        }
        return this.userRepository.saveOrUpdateUser(user);
    }

}
