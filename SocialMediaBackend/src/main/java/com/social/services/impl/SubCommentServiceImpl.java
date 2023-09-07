package com.social.services.impl;

import com.social.dto.request.ReplyRequest;
import com.social.exceptions.NotFoundException;
import com.social.pojo.Post;
import com.social.pojo.SubComment;
import com.social.pojo.User;
import com.social.repositories.PostRepository;
import com.social.repositories.SubCommentRepository;
import com.social.repositories.UserRepository;
import com.social.services.PostService;
import com.social.services.SubCommentService;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Lazy
@Service
@Transactional
public class SubCommentServiceImpl implements SubCommentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubCommentRepository subCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }

    @Override
    public SubComment save(ReplyRequest replyRequest) {
        // chưa xử lý đc
        Post currentPost = this.postRepository.getPostById(Integer.SIZE).get();
        if (currentPost == null) {
            throw new NotFoundException();
        } else if (currentPost.getLockComment()) {
            return null;
        }
        SubComment subComment = mapper.map(replyRequest, SubComment.class);
        subComment.setUser(getCurrentUser());
        return this.subCommentRepository.save(subComment);
    }

    @Override
    public boolean delete(Integer id) {
        SubComment subComment = this.subCommentRepository.getById(id);
        if (subComment == null) {
            throw new NotFoundException();
        }
        // Check that is ADMIN or OWNER 
        User user = getCurrentUser();
        if (!user.getRole().getName().equals("ROLE_ADMIN") //ADMIN
                && !user.getId().equals(subComment.getUser().getId()) //OWNER
                ) {
            throw new AccessDeniedException("You don't have permission to do that");
        }
        // Must delete all actions of reply here before delete reply (inside repo)
        return this.subCommentRepository.delete(subComment);
    }

    @Override
    public List<SubComment> getReplies(Map<String, String> params) {
        if (params != null && params.get("page") == null) {
            params.put("page", "1");
        }
        return this.subCommentRepository.getReplies(params);
    }
}
