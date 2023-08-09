package com.social.services.impl;

import com.social.dto.request.PostRequest;
import com.social.dto.request.SurveyRequest;
import com.social.pojo.Post;
import com.social.repositories.PostRepository;
import com.social.services.PostService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class PostServiceImpl implements PostService {
    
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPosts(Map<String, Object> params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Post getPostById(String id) {
        Optional<Post> post = this.postRepository.getPostById(id);
        return post.orElseThrow();
    }

    @Override
    public Post saveOrUpdatePost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Post saveSurveyPost(SurveyRequest surveyRequest) {
        return this.postRepository.saveSurvey(surveyRequest);
    }

    @Override
    public Post save(PostRequest post) {
        return postRepository.save(post);
    }
    
}
