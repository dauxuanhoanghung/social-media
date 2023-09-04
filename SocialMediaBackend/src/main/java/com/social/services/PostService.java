package com.social.services;

import com.social.dto.PostDTO;
import com.social.dto.request.PostRequest;
import com.social.dto.request.SurveyRequest;
import com.social.pojo.Post;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface PostService {

    List<Post> getPosts(Map<String, String> params);
    
    List<PostDTO> getPost(Integer page);
    
    Post getPostById(Integer id);
    
    Post saveSurveyPost(SurveyRequest surveyRequest);
    
    Post save(PostRequest post);
    
    Post toggleLockCOmment(Integer postId);
}
