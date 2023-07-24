package com.social.repositories;

import com.social.pojo.Comment;
import com.social.pojo.SubComment;

/**
 *
 * @author LENOVO
 */
public interface CommentRepository {
    
    Comment saveOrUpdate(Comment comment);
    SubComment saveOrUpdateSubComment(SubComment subComment, Comment comment);
}
