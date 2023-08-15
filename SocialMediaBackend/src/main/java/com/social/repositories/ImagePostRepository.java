package com.social.repositories;

import com.social.pojo.ImagePost;

/**
 *
 * @author LENOVO
 */
public interface ImagePostRepository {
    ImagePost save(ImagePost imagePost);
    boolean delete(String url);
}
