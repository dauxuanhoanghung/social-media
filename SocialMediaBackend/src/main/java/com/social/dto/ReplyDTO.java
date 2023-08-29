package com.social.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.social.dto.response.UserResponse;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReplyDTO {

    private Integer id;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime modifiedDate;
    private UserResponse user;

}
