package com.social.repositories;

import com.social.pojo.Comment;
import com.social.pojo.CommentAction;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface CommentRepository {

    Comment getCommentById(Integer id);

    List<Comment> getComments(Map<String, String> params);

    Long countActionById(int commentId);
    
    Long countSubCommentById(Integer commentId);

    Comment save(Comment comment);

    CommentAction save(CommentAction commentAction);

    Comment update(Comment comment);

    boolean delete(Comment comment);
    
    boolean deleteById(Integer commentId);
}
