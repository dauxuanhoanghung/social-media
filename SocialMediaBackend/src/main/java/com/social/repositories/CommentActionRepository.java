package com.social.repositories;

import com.social.pojo.CommentAction;
import java.util.Optional;

/**
 *
 * @author LENOVO
 */
public interface CommentActionRepository {

    Optional<CommentAction> get(Integer userId, Integer commentId);

    CommentAction update(CommentAction commentAction);

    CommentAction save(CommentAction commentAction);

    Integer countByCommentId(Integer commentId);
    
    boolean delete(CommentAction commentAction);
}
