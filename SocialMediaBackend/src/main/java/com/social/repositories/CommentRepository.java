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
    SubComment getSubCommentById(Integer id);
    
    List<Comment> getCommentsByPostId(int postId);
    List<SubComment> getRepliesByCommentId(int commentId);
    
    long countActionById(int commentId);
    long countReplyActionById(int replyId);
    
    Comment save(Comment comment);
    CommentAction save(CommentAction commentAction);
    SubComment save(SubComment subComment);
    SubCommentAction save(SubCommentAction subCommentAction);
    
    Comment update(Comment comment);
    SubComment update(SubComment subComment);
    
    boolean delete(Comment comment);
    boolean delete(SubComment subComment);
}
