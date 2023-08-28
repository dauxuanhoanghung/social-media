package com.social.services.impl;

import com.social.dto.request.CommentRequest;
import com.social.pojo.Comment;
import com.social.pojo.SubComment;
import com.social.pojo.User;
import com.social.repositories.CommentRepository;
import com.social.repositories.UserRepository;
import com.social.services.CommentService;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }
    
    @Override
    public Comment save(CommentRequest commentRequest) {       
        Comment comment = mapper.map(commentRequest, Comment.class);
        comment.setCountAction(0);
        comment.setUser(getCurrentUser());
        return this.commentRepository.save(comment);
    }

    @Override
    public List<Comment> getByPostId(Map<String, String> params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public List<SubComment> getRepliesByCommentId(Integer id) {
        return null;
    }
}
