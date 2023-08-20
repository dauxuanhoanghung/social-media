package com.social.dto.request;

import com.social.pojo.Post;
import lombok.Data;

/**
 *
 * @author LENOVO
 */
@Data
public class CommentRequest {
    
    private String content;
    private Post postId;
}
