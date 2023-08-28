package com.social.repositories.impl;

import com.social.pojo.CommentAction;
import com.social.repositories.CommentActionRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class CommentActionRepositoryImpl implements CommentActionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public CommentAction save(CommentAction commentAction) {
        Session session = getSession();

        try {
            session.save(commentAction);
            return commentAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public Integer countByCommentId(Integer commentId) {
        return 0;
    }

}
