package com.social.repositories;

import com.social.pojo.CommentAction;

/**
 *
 * @author LENOVO
 */
public interface CommentActionRepository {
    
    CommentAction save(CommentAction commentAction);
    Integer countByCommentId(Integer commentId);
}
