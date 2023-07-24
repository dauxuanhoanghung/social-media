/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories.impl;

import com.social.pojo.User;
import com.social.repositories.StatsRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Object[]> countUsers(Map<String, String> params) {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String fromDate = params.get("fromDate");
            if (fromDate != null && !fromDate.isEmpty()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(userRoot.get("createdDate"),
                        LocalDateTime.parse(fromDate, dateTimeFormatter)));
            }
            String toDate = params.get("toDate");
            if (fromDate != null && !fromDate.isEmpty()) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(userRoot.get("createdDate"),
                        LocalDateTime.parse(toDate, dateTimeFormatter)));
            }
        }
        
        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<Object[]> countPosts(Map<String, String> params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
