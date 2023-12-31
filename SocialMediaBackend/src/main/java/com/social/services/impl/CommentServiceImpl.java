package com.social.services.impl;

import com.social.dto.request.CommentRequest;
import com.social.exceptions.NotFoundException;
import com.social.pojo.Comment;
import com.social.pojo.Post;
import com.social.pojo.User;
import com.social.repositories.CommentRepository;
import com.social.repositories.UserRepository;
import com.social.services.CommentService;
import com.social.services.PostService;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostService postService;

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }

    @Override
    public Comment save(CommentRequest commentRequest) {
        Post currentPost = this.postService.getPostById(commentRequest.getPostId().getId());
        if (currentPost == null) {
            throw new NotFoundException();
        }
        else if (currentPost.getLockComment()) {
            return null;
        }
        Comment comment = mapper.map(commentRequest, Comment.class);
        comment.setUser(getCurrentUser());
        return this.commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(Map<String, String> params) {
        if (params != null) {
            if (params.get("page") == null) {
                params.put("page", "1");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
                params.put("alumniId", authentication.getName());
            }
        }
        return this.commentRepository.getComments(params);
    }

    @Override
    public boolean delete(Integer id) {
        Comment comment = this.commentRepository.getCommentById(id);
        if (comment == null) {
            return false;
        } else {
            // Delete inside repo (subAction, subComment, commentAction)
            return this.commentRepository.delete(comment);
        }
    }

    @Override
    public Long countRepliesByCommentId(Integer commentId) {
        return this.commentRepository.countSubCommentById(commentId);
    }
}
