/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories.impl;

import com.social.pojo.Role;
import com.social.repositories.RoleRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleRepositoryImpl implements RoleRepository {
    
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
    public List<Role> getAll() {
        Session session = getSession();
        Query query = session.createNamedQuery("Role.findAll", Role.class);
        return query.getResultList();
    }  
}
