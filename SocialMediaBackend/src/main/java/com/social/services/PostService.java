package com.social.services;

import com.social.dto.request.PostRequest;
import com.social.dto.request.SurveyRequest;
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
    
    Post saveSurveyPost(SurveyRequest surveyRequest);
    
    Post save(PostRequest post);
}
