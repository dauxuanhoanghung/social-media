package com.social.repositories.impl;

import com.social.pojo.Tags;
import com.social.repositories.TagsRepository;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class TagsRepositoryImpl implements TagsRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Tags save(Tags tag) {
        Session session = getSession();

        try {
            session.save(tag);
            return tag;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public Optional<Tags> getByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Tags> getById(Integer id) {
        Session session = getSession();
        try {
            Tags tags = session.get(Tags.class, id);
            return Optional.ofNullable(tags);
        } catch (NoResultException ex) {
            // If no result is found, return an empty Optional
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Tags tags) {
        Session session = getSession();

        try {
            session.delete(tags);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer tagsId) {
        Session session = getSession();
        return this.delete(session.get(Tags.class, tagsId));
    }

}
