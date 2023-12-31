package com.social.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.social.enums.UserStatus;
import com.social.pojo.Role;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String alumniId;
    private String displayName;
    private String slug;
    private String email;
    private String coverBg;
    private String avatar;
    private UserStatus status;
    private Role role;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
}
