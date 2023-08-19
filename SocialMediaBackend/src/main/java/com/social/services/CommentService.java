package com.social.services;

import com.social.pojo.Comment;
import com.social.pojo.SubComment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface CommentService {
    
    Comment save(Comment comment);
    SubComment save(SubComment subComment);
    
    List<Comment> getByPostId(Map<String, String> params);
    
}
