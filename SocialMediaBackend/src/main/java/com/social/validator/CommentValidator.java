package com.social.validator;

import com.social.dto.request.CommentRequest;
import com.social.dto.request.ReplyRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentRequest.class.isAssignableFrom(clazz)
                || ReplyRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof CommentRequest) {
            validateCommentRequest((CommentRequest) target, errors);
        } else if (target instanceof ReplyRequest) {
            validateReplyRequestRequest((ReplyRequest) target, errors);
        } else {
            throw new IllegalArgumentException("Unsupported target class for validation: " + target.getClass());
        }
    }

    private void validateCommentRequest(CommentRequest target, Errors errors) {
        if (target.getPostId() == null) {
            errors.rejectValue("postId", "validator.commentRequest.postId",
                    "Lack of field postId");
        }
        if (target.getContent() == null || target.getContent().isBlank()) {
            errors.rejectValue("content", "validator.commentRequest.content",
                    "Please enter your comment");
        } else if (target.getContent().length() > 255) {
            errors.rejectValue("content", "validator.commentRequest.content.length",
                    "Comment is too long, longer than 255 is not valid");
        }
    }

    private void validateReplyRequestRequest(ReplyRequest target, Errors errors) {
        if (target.getComment() == null) {
            errors.rejectValue("comment", "validator.replyRequest.comment",
                    "Lack of field comment");
        }
        if (target.getContent() == null || target.getContent().isBlank()) {
            errors.rejectValue("content", "validator.replyRequest.content.length",
                    "Reply is too long, longer than 255 is not valid");
        } else if (target.getContent().length() > 255) {
            errors.rejectValue("content", "validator.replyRequest.content.length",
                    "Comment is too long, longer than 255 is not valid");
        }
    }
}
