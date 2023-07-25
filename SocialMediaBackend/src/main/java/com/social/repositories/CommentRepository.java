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
    
    Comment saveOrUpdateComment(Comment comment);
    CommentAction saveOrUpdateCommentAction(CommentAction commentAction);
    SubComment saveOrUpdateSubComment(SubComment subComment);
    SubCommentAction saveOrUpdateSubCommentAction(SubCommentAction subCommentAction);
}
