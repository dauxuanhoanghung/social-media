package com.social.repositories.impl;

import com.social.pojo.User;
import com.social.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author DinhChuong
 */
@Repository
@Transactional
@PropertySource("classpath:/application.properties")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Environment env;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session session = getSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("id")));

        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = this.env.getProperty("PAGINATION", Integer.class);

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }

        return query.getResultList();
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
        return Optional.ofNullable(
                (User) query.getResultList()
                        .stream().findFirst().orElse(null)
        );
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
                s.save(user);
            } else {
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
