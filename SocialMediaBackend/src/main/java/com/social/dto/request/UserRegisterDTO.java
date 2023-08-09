package com.social.dto.request;

import com.social.dto.UserDTO;
import com.social.enums.UserStatus;
import com.social.pojo.Role;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LENOVO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String password;

    protected Integer id;
    @NotBlank(message = "{userDTO.username.notBlank}")
    protected String alumniId;
    @NotBlank(message = "{userDTO.displayName.notBlank}")
    protected String displayName;
    protected MultipartFile avatarFile;
    @NotBlank(message = "{userDTO.email.notBlank}")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "{userDTO.email.pattern}")
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

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s", this.email, this.password, this.displayName, this.role);
    }
}
