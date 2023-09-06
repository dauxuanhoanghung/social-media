package com.social.services.impl;

import com.social.dto.PostDTO;
import com.social.dto.request.AnswerRequest;
import com.social.dto.request.PostRequest;
import com.social.dto.request.QuestionRequest;
import com.social.dto.request.SurveyRequest;
import com.social.enums.PostType;
import com.social.enums.QuestionType;
import com.social.exceptions.NotFoundException;
import com.social.pojo.Choice;
import com.social.pojo.ImagePost;
import com.social.pojo.Post;
import com.social.pojo.Question;
import com.social.pojo.User;
import com.social.repositories.ImagePostRepository;
import com.social.repositories.PostActionRepository;
import com.social.repositories.PostRepository;
import com.social.repositories.QuestionRepository;
import com.social.repositories.TagsRepository;
import com.social.repositories.UserRepository;
import com.social.services.CloudinaryService;
import com.social.services.PostService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
    private PostActionRepository postActionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<Post> getPosts(Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (params != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            params.put("alumniId", authentication.getName());
        }
        List<Post> posts = this.postRepository.getPosts(params);
        return posts;
    }

    @Override
    public Post getPostById(Integer id) {
        Optional<Post> post = this.postRepository.getPostById(id);
        return post.orElseThrow();
    }

    @Override
    public Post saveSurveyPost(SurveyRequest surveyRequest) {
        Post surveyPost = mapper.map(surveyRequest, Post.class);
        surveyPost.setQuestions(null);
        surveyPost.setLockComment(Boolean.FALSE);
        surveyPost.setCountAction(0);
        surveyPost.setUser(getCurrentUser());
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
        Post savedPost = new Post();
        savedPost.setContent(post.getContent());
        savedPost.setCountAction(0);
        savedPost.setUser(getCurrentUser());
        savedPost.setLockComment(Boolean.FALSE);
        savedPost.setType(PostType.POST);
        this.postRepository.save(savedPost);
        if (post.getImages() != null && !post.getImages().isEmpty()) {
            for (MultipartFile file : post.getImages()) {
                String link = cloudinaryService.uploadImage(file);
                ImagePost imagePost = new ImagePost();
                imagePost.setPostId(savedPost);
                imagePost.setUrl(link);

                this.imagePostRepository.save(imagePost);
            }
        }

        return savedPost;
    }

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }

    @Override
    public Post toggleLockCOmment(Integer postId) {
        Post currentPost = this.postRepository.getPostById(postId).get();
        if (currentPost == null) {
            throw new NotFoundException();
        }
        User user = getCurrentUser();
        currentPost.setLockComment(!currentPost.getLockComment());
        return this.postRepository.save(currentPost);
    }

}
