package com.social.repositories.impl;

import com.social.pojo.SubCommentAction;
import com.social.repositories.SubCommentActionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class SubCommentActionRepositoryImpl implements SubCommentActionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    
    @Override
    public SubCommentAction save(SubCommentAction subCommentAction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
