package com.social.repositories;

import java.util.List;
import java.util.Map;

public interface SurveyResultRepository {

    /**
     *
     * @param params
     * @return columns: [list.question.content], row {user.displayName,
     * list.surveyResult.content}
     */
    List<Object[]> getResultSurvey(Map<String, String> params);
}
