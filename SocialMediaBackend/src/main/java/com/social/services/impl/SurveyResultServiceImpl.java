package com.social.services.impl;

import com.social.pojo.Question;
import com.social.repositories.SurveyResultRepository;
import com.social.services.SurveyResultService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SurveyResultServiceImpl implements SurveyResultService {

    @Autowired
    private SurveyResultRepository surveyResultRepository;

    @Override
    public List<Object[]> getResultSurvey(Map<String, String> params) {
        return surveyResultRepository.getResultSurvey(params);
    }

    @Override
    public List<Question> getQuestionsByPostId(int postId) {
        return surveyResultRepository.getQuestionsByPostId(postId);
    }

}
