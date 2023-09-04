package com.social.repositories;

import com.social.enums.Action;
import com.social.pojo.PostAction;
import java.util.Optional;

/**
 *
 * @author LENOVO
 */
public interface PostActionRepository {

    Optional<PostAction> get(Integer userId, Integer postId);

    Optional<Action> get(String alumniId, Integer postId);

    PostAction update(PostAction postAction);

    PostAction save(PostAction postAction);

    boolean delete(PostAction postAction);

    boolean deleteById(Integer postActionId);
}
