package com.social.repositories.impl;

import com.social.pojo.PostAction;
import com.social.repositories.PostActionRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class PostActionRepositoryImpl implements PostActionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public PostAction save(PostAction postAction) {
        Session session = getSession();

        try {
            session.save(postAction);
            return postAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(PostAction postAction) {
        Session session = getSession();

        try {
            session.delete(postAction);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer postActionId) {
        Session session = getSession();
        return this.delete(session.get(PostAction.class, postActionId));
    }

}
