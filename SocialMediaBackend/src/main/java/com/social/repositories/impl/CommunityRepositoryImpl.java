/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.repositories.impl;

import com.social.pojo.Community;
import com.social.pojo.User;
import com.social.repositories.CommunityRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DinhChuong
 */
@Repository
@Transactional
@PropertySource("classpath:/application.properties")
public class CommunityRepositoryImpl implements CommunityRepository {

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
    public List<Community> getCommunities(Map<String, String> params) {
        Session session = getSession();
        String hql = "FROM Community c WHERE (:id IS NULL OR c.id = :id)";
        Query<Community> query = session.createQuery(hql, Community.class);
        query.setParameter("id", params.getOrDefault("groupId", null));
        return query.list();
    }

    @Override
    public Community addUser(int communityId, List<User> users) {
        Session session = getSession();
        try {
            // Step 1: Retrieve the community by ID
            Community community = session.get(Community.class, communityId);

            if (community != null) {
                // Step 2: Update the community by adding users
                community.getUsers().addAll(users); // Assuming you have a getUsers() method in Community

                // Step 3: Save the updated community
                session.update(community);
                return community;
            } else {
                // Handle the case where the community with the given ID doesn't exist
                return null;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteCommunity(int communityId) {
        Session session = getSession();
        try {
            // Step 1: Retrieve the community by ID
            Community community = session.get(Community.class, communityId);

            if (community != null) {
                // Step 2: Delete the community
                session.delete(community);

                // Optionally, you can return the deleted community here if needed
                return true;
            } else {
                // Handle the case where the community with the given ID doesn't exist
                return false;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Community toggleActiveCommunity(int communityId, boolean status) {
        Session session = getSession();
        try {
            // Step 1: Retrieve the community by ID
            Community community = session.get(Community.class, communityId);

            if (community != null) {
                // Step 2: Update the 'active' status of the community
                String rs = status == true ? "ACTIVE" : "DEACTIVE";
                community.setStatus(rs);

                // Step 3: Save the updated community
                session.update(community);

                // Optionally, you can return the updated community here if needed
                return community;
            } else {
                // Handle the case where the community with the given ID doesn't exist
                return null;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Community createCommunity(Community community) {
        Session session = getSession();
        try {
            session.save(community);
            return community;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Community removeUser(int communityId, List<User> myUsers) {
        Session session = getSession();
        try {
            // Step 1: Retrieve the community by ID
            Community community = session.get(Community.class, communityId);

            if (community != null) {
                // Step 2: Get the set of users in the community
                Set<User> users = community.getUsers();

                // Step 3: Remove the users from the community using Java streams
                users.removeAll(myUsers);

                // Step 4: Save the updated community
                session.update(community);
                return community;
            } else {
                // Handle the case where the community with the given ID doesn't exist
                return null;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
