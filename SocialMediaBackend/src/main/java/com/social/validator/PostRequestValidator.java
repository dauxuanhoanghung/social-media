package com.social.validator;

import com.social.dto.request.PostRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author LENOVO
 */
@Component
public class PostRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostRequest postRequest = (PostRequest) target;
        if ((postRequest.getContent() == null 
                || postRequest.getContent().isBlank()) 
                || (postRequest.getImages() == null
                || postRequest.getImages().isEmpty())) {
            errors.reject("validator.postRequest", "POST is empty");
        }
    }

}
