package com.social.repositories;

import com.social.pojo.Comment;
import com.social.pojo.CommentAction;
import com.social.pojo.SubComment;
import com.social.pojo.SubCommentAction;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface CommentRepository {

    Comment getCommentById(Integer id);

    List<Comment> getCommentsByPostId(int postId);

    long countActionById(int commentId);

    Comment save(Comment comment);

    CommentAction save(CommentAction commentAction);

    Comment update(Comment comment);

    boolean delete(Comment comment);
    
    boolean deleteById(Integer commentId);
}
