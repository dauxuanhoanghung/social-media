package com.social.repositories.impl;

import com.social.pojo.CommentAction;
import com.social.repositories.CommentActionRepository;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class CommentActionRepositoryImpl implements CommentActionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public CommentAction save(CommentAction commentAction) {
        Session session = getSession();

        try {
            session.save(commentAction);
            return commentAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public Integer countByCommentId(Integer commentId) {
        return 0;
    }

    @Override
    public Optional<CommentAction> get(Integer userId, Integer commentId) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<CommentAction> criteriaQuery = criteriaBuilder.createQuery(CommentAction.class);
        Root<CommentAction> commentActionRoot = criteriaQuery.from(CommentAction.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(commentActionRoot.get("comment"), commentId));
        predicates.add(criteriaBuilder.equal(commentActionRoot.get("user"), userId));
        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        try {
            Query query = session.createQuery(criteriaQuery);
            return Optional.ofNullable((CommentAction) query.getResultList().get(0));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public CommentAction update(CommentAction commentAction) {
        Session session = getSession();
        try {
            session.update(commentAction);
            return commentAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(CommentAction commentAction) {
        Session session = getSession();
        try {
            session.delete(commentAction);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

}
