package com.social.repositories.impl;

import com.social.pojo.Choice;
import com.social.repositories.ChoiceRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LENOVO
 */
@Repository
public class ChoiceRepositoryImpl implements ChoiceRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
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

    @Override
    public boolean delete(Choice choice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteById(Integer choiceId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
