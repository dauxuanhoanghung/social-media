package com.social.validator;

import com.social.dto.request.AnswerRequest;
import com.social.dto.request.QuestionRequest;
import com.social.dto.request.SurveyRequest;
import com.social.enums.QuestionType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SurveyRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SurveyRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SurveyRequest surveyRequest = (SurveyRequest) target;
        // Check that have questions ?
        // NO QUESTION
        if (surveyRequest.getQuestions() == null || surveyRequest.getQuestions().isEmpty()) {
            errors.rejectValue("questions",
                    "validator.surveyRequest.questions.required", "At least one question is required");
            return;
        }
        for (QuestionRequest questionRequest : surveyRequest.getQuestions()) {
            // Check question content not null
            if (questionRequest.getContent().isBlank()) {
                errors.rejectValue("content", "validator.questionRequest.content.notNull",
                        "Question content must have text");
            }
            // Check answer content not null, if type = text -> no answer so need to continue
            if (questionRequest.getQuestionType().equals(QuestionType.TEXT)) {
                continue;
            }
            for (AnswerRequest answer : questionRequest.getAnswers()) {
                if (answer.getContent().isBlank()) {
                    errors.rejectValue("content", "validator.questionRequest.answers.content",
                            "Options for question must have text");
                }
            }
        }

    }

}
