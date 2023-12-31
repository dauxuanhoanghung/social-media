package com.social.repositories.impl;

import com.social.enums.Action;
import com.social.pojo.PostAction;
import com.social.pojo.User;
import com.social.repositories.PostActionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class PostActionRepositoryImpl implements PostActionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public PostAction save(PostAction postAction) {
        Session session = getSession();

        try {
            session.save(postAction);
            return postAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(PostAction postAction) {
        Session session = getSession();

        try {
            session.delete(postAction);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer postActionId) {
        Session session = getSession();
        return this.delete(session.get(PostAction.class, postActionId));
    }

    @Override
    public Optional<PostAction> get(Integer userId, Integer postId) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<PostAction> criteriaQuery = criteriaBuilder.createQuery(PostAction.class);
        Root<PostAction> commentActionRoot = criteriaQuery.from(PostAction.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(commentActionRoot.get("post"), postId));
        predicates.add(criteriaBuilder.equal(commentActionRoot.get("user"), userId));
        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        try {
            Query query = session.createQuery(criteriaQuery);
            return Optional.ofNullable((PostAction) query.getResultList().get(0));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public PostAction update(PostAction postAction) {
        Session session = getSession();
        try {
            session.update(postAction);
            return postAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public Optional<Action> get(String alumniId, Integer postId) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Action> subquery = criteriaBuilder.createQuery(Action.class);
        Root<PostAction> postActionRoot = subquery.from(PostAction.class);
        Root<User> userRoot = subquery.from(User.class);
        subquery.select(postActionRoot.get("act"));
        List<Predicate> subPre = new ArrayList();
        subPre.add(criteriaBuilder.equal(userRoot.get("id"), postActionRoot.get("user")));
        subPre.add(criteriaBuilder.equal(userRoot.get("alumniId"), alumniId));
        subPre.add(criteriaBuilder.equal(postActionRoot.get("post"), postId));

        subquery.where(subPre.toArray(Predicate[]::new));
        Object a = session.createQuery(subquery).getResultList().get(0);
        try {
            if (a != null) {
                return Optional.ofNullable((Action) a);
            }
        } catch (HibernateException ex) {
            return Optional.empty();
        }
        return null;
    }

}
