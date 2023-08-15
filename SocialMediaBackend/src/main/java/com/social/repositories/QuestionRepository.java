package com.social.repositories;

import com.social.pojo.Choice;
import com.social.pojo.Question;

/**
 *
 * @author LENOVO
 */
public interface QuestionRepository {

    Question save(Question question);

    Choice save(Choice choice);
}
