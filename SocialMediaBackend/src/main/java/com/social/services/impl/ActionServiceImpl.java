package com.social.services.impl;

import com.social.pojo.Action;
import com.social.repositories.ActionRepository;
import com.social.services.ActionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public List<Action> getAll() {
        return this.actionRepository.getAll();
    }

}
