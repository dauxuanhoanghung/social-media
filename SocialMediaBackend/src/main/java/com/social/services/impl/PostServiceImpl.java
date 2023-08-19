package com.social.services.impl;

import com.social.dto.request.AnswerRequest;
import com.social.dto.request.PostRequest;
import com.social.dto.request.QuestionRequest;
import com.social.dto.request.SurveyRequest;
import com.social.enums.QuestionType;
import com.social.pojo.Choice;
import com.social.pojo.ImagePost;
import com.social.pojo.Post;
import com.social.pojo.Question;
import com.social.repositories.ImagePostRepository;
import com.social.repositories.PostRepository;
import com.social.repositories.QuestionRepository;
import com.social.repositories.UserRepository;
import com.social.services.CloudinaryService;
import com.social.services.PostService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LENOVO
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImagePostRepository imagePostRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> getPosts(Map<String, Object> params) {
        return this.postRepository.getPosts(params);
    }

    @Override
    public Post getPostById(String id) {
        Optional<Post> post = this.postRepository.getPostById(String.valueOf(id));
        return post.orElseThrow();
    }

    @Override
    public Post saveSurveyPost(SurveyRequest surveyRequest) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post surveyPost = mapper.map(surveyRequest, Post.class);
        surveyPost.setQuestions(null);
        surveyPost.setLockComment(Boolean.FALSE);
        surveyPost.setCountAction(0);
//        surveyPost.setUser(this.userRepository.getUserByAlumniId(authentication.getName()).get());
        this.postRepository.save(surveyPost);
        for (QuestionRequest qr : surveyRequest.getQuestions()) {
            Question question = mapper.map(qr, Question.class);
            question.setPost(surveyPost);
            question.setChoices(null);
            this.questionRepository.save(question);
            if (!question.getQuestionType().equals(QuestionType.TEXT)) {
                for (AnswerRequest ar : qr.getAnswers()) {
                    Choice choice = mapper.map(ar, Choice.class);
                    choice.setQuestion(question);
                    this.questionRepository.save(choice);
                }
            }
        }
        return surveyPost;
    }

    @Override
    public Post save(PostRequest post) {
//        Post savedPost = this.postRepository.save(post);
        Post savedPost = mapper.map(post, Post.class);
        this.postRepository.save(savedPost);
        for (MultipartFile file : post.getImages()) {
            String link = cloudinaryService.uploadImage(file);
            ImagePost imagePost = new ImagePost();
            imagePost.setPostId(savedPost);
            imagePost.setUrl(link);

            this.imagePostRepository.save(imagePost);
        }
        return savedPost;
    }

}
