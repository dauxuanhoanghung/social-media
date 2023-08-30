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
        return null;
    }

    @Override
    public PostAction saveOrUpdateOrDelete(PostActionRequest request) {
        return null;
    }

    @Override
    public SubCommentAction saveOrUpdateOrDelete(ReplyActionRequest request) {
        return null;
    }

}
