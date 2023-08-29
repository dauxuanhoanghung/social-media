package com.social.services;

import com.social.dto.request.CommentRequest;
import com.social.pojo.Comment;
import com.social.pojo.SubComment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface CommentService {

    Comment save(CommentRequest commentRequest);

    List<Comment> getComments(Map<String, String> params);

    List<SubComment> getRepliesByCommentId(Integer id);

    boolean delete(Integer id);
    
    Long countRepliesByCommentId(Integer commentId);
}
