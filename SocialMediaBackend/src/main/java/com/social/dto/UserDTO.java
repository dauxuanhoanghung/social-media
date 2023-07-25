package com.social.dto;

import com.social.enums.UserStatus;
import com.social.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DinhChuong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    protected Integer id;
    protected String username;
    protected String alumniId;
    protected String displayName;
    protected MultipartFile avatarFile;
    protected MultipartFile coverBgFile;
    protected String email;
    protected UserStatus status;
    protected String slug;
    protected Role role;

}
