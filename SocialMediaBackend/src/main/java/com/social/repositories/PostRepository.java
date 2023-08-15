package com.social.repositories;

import com.social.dto.request.PostRequest;
import com.social.pojo.Post;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author LENOVO
 */
public interface PostRepository {

    List<Post> getPosts(Map<String, Object> params);

    Optional<Post> getPostById(String id);

    Post save(Post post);
    
    Post save(PostRequest post);
    
    Post update(Post post);

    boolean deletePost(Integer id);
}
