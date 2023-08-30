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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
