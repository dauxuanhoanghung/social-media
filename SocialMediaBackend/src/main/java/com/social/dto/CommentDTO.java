package com.social.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.social.dto.response.UserResponse;
import com.social.enums.Action;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author LENOVO
 */
@Data
public class CommentDTO {

    private Integer id;
    private String content;
    private Integer countAction;
    private Long countReply;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime modifiedDate;
    private Action currentAction;
    private UserResponse user;
}
