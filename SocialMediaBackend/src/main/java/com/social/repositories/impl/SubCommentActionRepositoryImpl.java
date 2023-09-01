package com.social.repositories.impl;

import com.social.pojo.SubCommentAction;
import com.social.repositories.SubCommentActionRepository;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class SubCommentActionRepositoryImpl implements SubCommentActionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public SubCommentAction save(SubCommentAction subCommentAction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<SubCommentAction> get(Integer userId, Integer subCommentId) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<SubCommentAction> criteriaQuery = criteriaBuilder.createQuery(SubCommentAction.class);
        Root<SubCommentAction> subCommentActionRoot = criteriaQuery.from(SubCommentAction.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(subCommentActionRoot.get("subComment"), subCommentId));
        predicates.add(criteriaBuilder.equal(subCommentActionRoot.get("user"), userId));
        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        try {
            Query query = session.createQuery(criteriaQuery);
            return Optional.ofNullable((SubCommentAction) query.getResultList().get(0));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public SubCommentAction update(SubCommentAction subCommentAction) {
        Session session = getSession();
        try {
            session.update(subCommentAction);
            return subCommentAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(SubCommentAction subCommentAction) {
        Session session = getSession();
        try {
            session.delete(subCommentAction);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer subCommentActionId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
