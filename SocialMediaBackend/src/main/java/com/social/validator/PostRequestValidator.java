package com.social.validator;

import com.social.dto.request.PostRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author LENOVO
 */
public class PostRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PostRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostRequest postRequest = (PostRequest) target;
        if (postRequest.getContent().isBlank() || postRequest.getImages() == null 
                || postRequest.getImages().isEmpty())
            errors.reject("", "POST is empty");
    }

}
