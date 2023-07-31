package com.social.dto;

import com.social.enums.UserStatus;
import com.social.pojo.Role;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "{userDTO.username.notBlank}")
    protected String username;
    protected String alumniId;
    @NotBlank(message = "{userDTO.displayName.notBlank}")
    protected String displayName;
    protected MultipartFile avatarFile;
    protected MultipartFile coverBgFile;
    @NotBlank(message = "{userDTO.email.notBlank}")
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 
            message="{userDTO.email.pattern}")
    protected String email;
    protected UserStatus status;
    protected String slug;
    @NotNull(message = "{userDTO.role.notNull}")
    protected Role role;

    public void setSlug(String slug) {
        if (slug != null && slug.trim().isEmpty()) {
            this.slug = null;
        } else {
            this.slug = slug;
        }
    }

    public void setAlumniId(String alumniId) {
        if (alumniId != null && alumniId.trim().isEmpty()) {
            this.alumniId = null;
        } else {
            this.alumniId = alumniId;
        }
    }

    public void setEmail(String email) {
        if (email != null && email.trim().isEmpty()) {
            this.email = null;
        } else {
            this.email = email;
        }
    }

}
