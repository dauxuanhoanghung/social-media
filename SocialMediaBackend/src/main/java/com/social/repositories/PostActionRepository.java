package com.social.repositories;

import com.social.pojo.PostAction;

/**
 *
 * @author LENOVO
 */
public interface PostActionRepository {

    PostAction save(PostAction postAction);

    boolean delete(PostAction postAction);
    
    boolean deleteById(Integer postActionId);
}
