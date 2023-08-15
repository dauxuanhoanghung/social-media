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
    
    List<Comment> getCommentsByPostId(int postId);
    List<SubComment> getReplyByCommentId(int commentId);
    
    long countActionById(int commentId);
    long countReplyActionById(int replyId);
    
    Comment save(Comment comment);
    CommentAction save(CommentAction commentAction);
    SubComment save(SubComment subComment);
    SubCommentAction save(SubCommentAction subCommentAction);
    
    Comment update(Comment comment);
    SubComment update(SubComment subComment);
}
