package com.social.validator;

import com.social.dto.request.CommentActionRequest;
import com.social.dto.request.PostActionRequest;
import com.social.dto.request.ReplyActionRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ActionValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return PostActionRequest.class.isAssignableFrom(clazz)
                || CommentActionRequest.class.isAssignableFrom(clazz)
                || ReplyActionRequest.class.isAssignableFrom(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof PostActionRequest) {
            validatePostActionRequest((PostActionRequest) target, errors);
        } else if (target instanceof CommentActionRequest) {
            validateCommentActionRequest((CommentActionRequest) target, errors);
        } else if (target instanceof ReplyActionRequest) {
            validateReplyActionRequest((ReplyActionRequest) target, errors);
        } else {
            throw new IllegalArgumentException("Unsupported target class for validation: " + target.getClass());
        }
    }
    
    private void validatePostActionRequest(PostActionRequest target, Errors errors) {
        
    }
    
    private void validateCommentActionRequest(CommentActionRequest target, Errors errors) {
        
    }
    
    private void validateReplyActionRequest(ReplyActionRequest target, Errors errors) {
        
    }
}
