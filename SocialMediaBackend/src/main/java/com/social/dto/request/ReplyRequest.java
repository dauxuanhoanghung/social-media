package com.social.dto.request;

import com.social.pojo.Comment;
import lombok.Data;

/**
 *
 * @author LENOVO
 */
@Data
public class ReplyRequest {
    
    private String content;
    private Comment comment;
}
