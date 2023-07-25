package com.social.dto;

import com.social.pojo.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LENOVO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer id;
    private String content;
    private Boolean lockComment;
    private Integer countAction;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private User user;
    private List<String> images;
}
