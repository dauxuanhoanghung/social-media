/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.social.repositories;

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

    Post saveOrUpdatePost(Post post);
}
