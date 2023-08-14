package com.social.repositories;

import com.social.pojo.Comment;
import com.social.pojo.CommentAction;
import com.social.pojo.SubComment;
import com.social.pojo.SubCommentAction;

/**
 *
 * @author LENOVO
 */
public interface CommentRepository {
    
    Comment save(Comment comment);
    CommentAction save(CommentAction commentAction);
    SubComment save(SubComment subComment);
    SubCommentAction save(SubCommentAction subCommentAction);
    
    Comment update(Comment comment);
    SubComment update(SubComment subComment);
}
