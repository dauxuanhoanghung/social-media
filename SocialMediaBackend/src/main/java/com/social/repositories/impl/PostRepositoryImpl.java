/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories.impl;

import com.social.pojo.Post;
import com.social.repositories.PostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional
@PropertySource("classpath:application.properties")
public class PostRepositoryImpl implements PostRepository {

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
    public List<Post> getPosts(Map<String, Object> params) {
        Session session = getSession();
        Integer size = env.getProperty("PAGINATION", Integer.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

        Root<Post> postRoot = criteriaQuery.from(Post.class);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
//            String fromDate = params.get("fromDate");
//            if (fromDate != null && !fromDate.isEmpty()) {
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(userRoot.get("createdDate"),
//                        LocalDateTime.parse(fromDate, dateTimeFormatter)));
//            }
//            String toDate = params.get("toDate");
//            if (fromDate != null && !fromDate.isEmpty()) {
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(userRoot.get("createdDate"),
//                        LocalDateTime.parse(toDate, dateTimeFormatter)));
//            }
        }

        criteriaQuery.orderBy(criteriaBuilder.desc(postRoot.get("createdDate")));

        Query query = session.createQuery(criteriaQuery);
        query.setMaxResults(size);
        if (params != null) {
            String page = (String) params.get("page");
            if (page != null && !page.isEmpty()) {
                query.setFirstResult((Integer.parseInt(page) - 1) * size);
            } else {
                query.setFirstResult(0);
            }

        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Post> getPostById(String id) {
        Session session = getSession();
        return Optional.ofNullable(session.get(Post.class, id));
    }

    @Override
    public Post saveOrUpdatePost(Post post) {
        Session s = getSession();
        try {
            if (post.getId() == null) {
                s.save(post);
            } else {
                s.update(post);
            }

            return post;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deletePost(Integer id) {
        Session session = getSession();
        Post post = session.get(Post.class, id);
        try {
            if (post != null) {
                session.delete(post);
                return true;
            }
            return false;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
