package com.social.services.impl;

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
import com.social.repositories.UserRepository;
import com.social.services.CloudinaryService;
import com.social.services.PostService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        surveyPost.setCountAction(0l);
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
        savedPost.setCountAction(0l);
        savedPost.setUser(getCurrentUser());
        savedPost.setLockComment(Boolean.FALSE);
        savedPost.setType(PostType.POST);
        savedPost.setImagePostSet(new HashSet<>());
        this.postRepository.save(savedPost);
        if (post.getImages() != null && !post.getImages().isEmpty()) {
            for (MultipartFile file : post.getImages()) {
                String link = cloudinaryService.uploadImage(file);
                ImagePost imagePost = new ImagePost();
                imagePost.setPostId(savedPost);
                imagePost.setUrl(link);
                this.imagePostRepository.save(imagePost);
                Set h = savedPost.getImagePostSet();
                h.add(imagePost);
                savedPost.setImagePostSet(h);

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
        if (!user.getRole().getName().equals("ROLE_ADMIN")
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
                && currentPost.getUser() != null
                && currentPost.getUser().getId() != user.getId()) {
            return null;
        }
        currentPost.setLockComment(!currentPost.getLockComment());
        return this.postRepository.save(currentPost);
    }

    @Override
    public Post toggleBlockComment(int postId) {
        User user = getCurrentUser();
        Set<Post> posts = user.getPosts();
        Post toggleBlockPost = posts.stream()
                .filter(post -> post.getId() == postId)
                .findFirst()
                .orElse(null);
        if (toggleBlockPost != null) {
            toggleBlockPost.setLockComment(!toggleBlockPost.getLockComment());
            return postRepository.update(toggleBlockPost);
        }
        return toggleBlockPost;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #post.user.alumniId == authentication.principal.username")
    public boolean delete(int postId) {
        Post post = postRepository.getPostById(postId).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (post != null) {
            if (post.getUser().getAlumniId().equals(authentication.getName()) //OWNER
                    || authentication.getAuthorities().stream()
                            .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                return postRepository.delete(post);
            }
        }

        return false;
    }

}
