package com.social.repositories;

import com.social.pojo.SubCommentAction;
import java.util.Optional;

/**
 *
 * @author LENOVO
 */
public interface SubCommentActionRepository {

    Optional<SubCommentAction> get(Integer userId, Integer subCommentActionId);

    SubCommentAction update(SubCommentAction subCommentAction);

    SubCommentAction save(SubCommentAction subCommentAction);

    boolean delete(SubCommentAction subCommentAction);

    boolean deleteById(Integer subCommentActionId);
}
