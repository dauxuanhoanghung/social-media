package com.social.services.impl;

import com.social.dto.request.CommentActionRequest;
import com.social.dto.request.PostActionRequest;
import com.social.dto.request.ReplyActionRequest;
import com.social.pojo.CommentAction;
import com.social.pojo.PostAction;
import com.social.pojo.SubCommentAction;
import com.social.pojo.User;
import com.social.repositories.CommentActionRepository;
import com.social.repositories.PostActionRepository;
import com.social.repositories.SubCommentActionRepository;
import com.social.repositories.UserRepository;
import com.social.services.ActionService;
import java.util.Optional;
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
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PostActionRepository postActionRepository;

    @Autowired
    private CommentActionRepository commentActionRepository;

    @Autowired
    private SubCommentActionRepository subCommentActionRepository;

    @Autowired
    private UserRepository userRepository;

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }

    @Override
    public CommentAction saveOrUpdateOrDelete(CommentActionRequest request) {
        // GET
        User currentUser = getCurrentUser();
        Optional<CommentAction> action
                = commentActionRepository.get(currentUser.getId(), request.getComment().getId());
        // SAVE
        if (action.isEmpty() && request.getAction() != null) {
            CommentAction savedEntity = mapper.map(request, CommentAction.class);
            savedEntity.setUser(currentUser);
            commentActionRepository.save(savedEntity);
            return savedEntity;
        } // DELETE
        else if (action.isPresent() && request.getAction() == null) {
            commentActionRepository.delete(action.get());
            return action.get();
        } else if (action.isPresent() && request.getAction() != null) {
            action.get().setAction(request.getAction());
            commentActionRepository.update(action.get());
            return action.get();
        } else {
            return null;
        }
    }

    @Override
    public PostAction saveOrUpdateOrDelete(PostActionRequest request) {
        try {
            // GET
            User currentUser = getCurrentUser();
            Optional<PostAction> action
                    = postActionRepository.get(currentUser.getId(), request.getPost().getId());
            // SAVE
            if (action.isEmpty() && request.getAction() != null) {
                PostAction savedEntity = mapper.map(request, PostAction.class);
                savedEntity.setUser(currentUser);
                postActionRepository.save(savedEntity);
                return savedEntity;
            } // DELETE
            else if (action.isPresent() && request.getAction() == null) {
                postActionRepository.delete(action.get());
                return action.get();
            } else if (action.isPresent() && request.getAction() != null) {
                action.get().setAction(request.getAction());
                postActionRepository.update(action.get());
                return action.get();
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public SubCommentAction saveOrUpdateOrDelete(ReplyActionRequest request) {
        // GET
        User currentUser = getCurrentUser();
        Optional<SubCommentAction> action
                = subCommentActionRepository.get(currentUser.getId(), request.getSubComment().getId());
        // SAVE
        if (action.isEmpty() && request.getAction() != null) {
            SubCommentAction savedEntity = new SubCommentAction();
            savedEntity.setAction(request.getAction());
            savedEntity.setSubComment(request.getSubComment());
            savedEntity.setUser(currentUser);
            subCommentActionRepository.save(savedEntity);
            return savedEntity;
        } // DELETE
        else if (action.isPresent() && request.getAction() == null) {
            subCommentActionRepository.delete(action.get());
            return action.get();
        } else if (action.isPresent() && request.getAction() != null) {
            action.get().setAction(request.getAction());
            subCommentActionRepository.update(action.get());
            return action.get();
        }
        return null;
    }

}
