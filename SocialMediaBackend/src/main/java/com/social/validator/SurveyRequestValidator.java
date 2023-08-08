package com.social.validator;

import com.social.dto.request.SurveyRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author LENOVO
 */
@Component
public class SurveyRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SurveyRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SurveyRequest surveyRequest = (SurveyRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "questions", 
                "validator.surveyRequest.questions.required", "At least one question is required");

        if (surveyRequest.getQuestions() != null && surveyRequest.getQuestions().isEmpty()) {
            errors.rejectValue("questions", 
                    "validator.surveyRequest.questions.required", "At least one question is required");
        }
    }
    
}
