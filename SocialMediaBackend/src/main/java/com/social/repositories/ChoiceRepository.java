package com.social.repositories;

import com.social.pojo.Choice;

/**
 *
 * @author LENOVO
 */
public interface ChoiceRepository {

    Choice save(Choice choice);

    boolean delete(Choice choice);

    boolean deleteById(Integer choiceId);
}
