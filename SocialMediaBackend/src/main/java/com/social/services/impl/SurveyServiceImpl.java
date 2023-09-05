package com.social.services.impl;

import com.social.dto.request.QuestionAndResult;
import com.social.dto.request.ResultSurveyRequest;
import com.social.pojo.SurveyResult;
import com.social.pojo.User;
import com.social.repositories.SurveyRepository;
import com.social.repositories.UserRepository;
import com.social.services.SurveyService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DinhChuong
 */
@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SurveyRepository surveyRepository;
    
    @Override
    public List<SurveyResult> save(ResultSurveyRequest result) {
        List<SurveyResult> rs = new ArrayList<>();
        for (QuestionAndResult answer : result.getAnswers()) {
            SurveyResult myResult = new SurveyResult();
            myResult.setQuestionId(answer.getQuestion());
            myResult.setResult(answer.getResult());
            myResult.setUserId(getCurrentUser());
            rs.add(myResult);
        }
        surveyRepository.save(rs);

        return rs;
    }

    // Get current user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.getUserByAlumniId(authentication.getName()).get();
    }
}
