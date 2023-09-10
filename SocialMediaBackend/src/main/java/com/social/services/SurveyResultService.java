package com.social.services;

import com.social.pojo.Question;
import java.util.List;
import java.util.Map;

public interface SurveyResultService {

    List<Object[]> getResultSurvey(Map<String, String> params);

    List<Question> getQuestionsByPostId(int postId);
}
