package com.social.repositories;

import com.social.pojo.ImagePost;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface ImagePostRepository {

    ImagePost save(ImagePost imagePost);

    boolean delete(String url);

    List<String> getImageUrls(Map<String, String> params);
}
