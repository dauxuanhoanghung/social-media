package com.social.repositories.impl;

import com.social.pojo.Choice;
import com.social.pojo.Question;
import com.social.repositories.QuestionRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Question save(Question question) {
        Session session = getSession();

        try {
            session.save(question);
            return question;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public Choice save(Choice choice) {
        Session session = getSession();

        try {
            session.save(choice);
            return choice;
        } catch (HibernateException ex) {
            return null;
        }
    }

}
