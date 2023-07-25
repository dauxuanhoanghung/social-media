package com.social.services;

import com.social.pojo.Post;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author LENOVO
 */
public interface PostService {

    List<Post> getPosts(Map<String, Object> params);

    Post getPostById(String id);

    Post saveOrUpdatePost(Post post);
}
