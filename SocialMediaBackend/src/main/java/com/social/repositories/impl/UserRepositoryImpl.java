package com.social.repositories.impl;

import com.social.pojo.User;
import com.social.repositories.UserRepository;
import java.util.Optional;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author DinhChuong
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = getSession();
        Query query = session.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public Optional<User> getUserByAlumniId(String alumniId) {
        Session session = getSession();
        Query query = session.createNamedQuery("User.findByAlumniId", User.class);
        query.setParameter("alumniId", alumniId);
        return Optional.ofNullable((User) query.getSingleResult());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Session session = getSession();
        Query query = session.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        return Optional.ofNullable((User) query.getSingleResult());
    }

    @Override
    public Optional<User> getUserBySlug(String slug) {
        Session session = getSession();
        Query query = session.createNamedQuery("User.findBySlug", User.class);
        query.setParameter("slug", slug);
        return Optional.ofNullable((User) query.getSingleResult());
    }

    @Override
    public User saveOrUpdateUser(User user) {
        Session s = getSession();
        try {
            if (user.getId() == null) {
                System.out.println("com.social.repositories.impl.UserRepositoryImpl.saveUser()");
                s.save(user);
            } else {
                System.out.println("com.social.repositories.impl.UserRepositoryImpl.UpdateUser()");
                s.update(user);
            }

            return user;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUser(Integer id) {
        Session session = getSession();
        User user = session.get(User.class, id);
        try {
            if (user != null) {
                session.delete(user);
                return true;
            }
            return false;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
