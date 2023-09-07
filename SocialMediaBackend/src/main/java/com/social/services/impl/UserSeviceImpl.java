package com.social.services.impl;

import com.social.dto.request.UserRegisterDTO;
import com.social.enums.UserStatus;
import com.social.exceptions.NotFoundException;
import com.social.pojo.Role;
import com.social.pojo.User;
import com.social.repositories.RoleRepository;
import com.social.repositories.UserRepository;
import com.social.services.CloudinaryService;
import com.social.services.MailService;
import com.social.services.UserService;
import com.social.utils.CloudinaryUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private CloudinaryService cloudinaryService;

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }

    @Override
    public List<Role> getAllRoles() {
        return this.roleRepository.getAll();
    }

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
        User user = this.userRepository.getUserById(id).get();
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
        User user = this.userRepository.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email User not found", email, "/users/"));
        return user;
    }

    @Override
    public User getUserBySlug(String slug) {
        User user = this.userRepository.getUserBySlug(slug)
                .orElseThrow(() -> new NotFoundException("User not found", slug, "/users/" + slug));
        return user;
    }

    @Override
    public boolean deleteUser(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User saveOrUpdateUser(User user) {
        if (user.getStatus().equals(UserStatus.ACTIVE)) {
            mailService.sendMail(user.getEmail(), "Thư chấp nhận",
                    "Tài khoản của bạn đã được xác nhận", "invitation");
        }
        return this.userRepository.update(user);
    }

    @Override
    public User save(UserRegisterDTO user) {
        if (this.userRepository.existsByAlumniId(user.getAlumniId())) {
            return null; // user exists
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User entity = modelMapper.map(user, User.class);
        entity.setCoverBg("");
        if (user.getAvatarFile() != null && !user.getAvatarFile().isEmpty()) {
            String url = cloudinaryService.uploadImage(user.getAvatarFile());
            entity.setAvatar(url);
        } else {
            entity.setAvatar("https://res.cloudinary.com/dzgugrqxz/image/upload/v1683031039/building_img/r3owjpmhhcehj29hgw2y.jpg");
        }
        return this.userRepository.save(entity);
    }

    @Override
    public User updateInfo(User user) {
        return this.userRepository.update(user);
    }

    @Override
    public User update(User user) {
        return this.userRepository.update(user);
    }

    @Override
    public User updateStatus(User user) {
        if (user.getStatus().equals(UserStatus.ACTIVE)) {
            mailService.sendMail(user.getEmail(), "Thư chấp nhận",
                    "Tài khoản của bạn đã được xác nhận", "invitation");
        }
        return update(user);
    }

    @Override
    public User updateAvatar(MultipartFile avatarFile, Boolean isBackground) {
        User user = getCurrentUser();
        try {
            String url = cloudinaryService.uploadImage(avatarFile);
            if (!isBackground) {
                if (user.getAvatar() != null && !user.getAvatar().isBlank()) {
                    String publicId = CloudinaryUtil.extractPublicIdFromUrl(user.getAvatar());
                    cloudinaryService.deleteImage(publicId);
                }
            } else {
                if (user.getCoverBg()!= null && !user.getCoverBg().isBlank()) {
                    String publicId = CloudinaryUtil.extractPublicIdFromUrl(user.getAvatar());
                    cloudinaryService.deleteImage(publicId);
                }
            }
            this.userRepository.updateAvatarOrBg(user.getAlumniId(), url, isBackground);
            return getCurrentUser();
        } catch (Exception ex) {
            Logger.getLogger(UserSeviceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public User activeLecture(String password, String oldPassword) {
        User user = getCurrentUser();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password));
            user.setStatus(UserStatus.ACTIVE);
            return userRepository.update(user);
        }
        return null;
    }

}
