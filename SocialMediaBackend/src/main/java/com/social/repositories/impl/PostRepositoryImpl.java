/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories.impl;

import com.social.pojo.Post;
import com.social.pojo.User;
import com.social.repositories.PostRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public List<Post> getPosts(Map<String, Object> params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Post> getPostById(String id) {
        Session session = getSession();
        return Optional.ofNullable(session.get(Post.class, id));
    }

    @Override
    public Post saveOrUpdatePost(Post post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
