package com.social.dto.response;

import com.social.enums.UserStatus;
import com.social.pojo.Role;
import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String alumniId;
    private String displayName;
    private String slug;
    private String coverBg;
    private String avatar;
    private UserStatus status;
    private Role role;
}
