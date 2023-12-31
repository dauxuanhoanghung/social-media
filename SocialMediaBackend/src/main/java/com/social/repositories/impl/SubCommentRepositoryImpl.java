package com.social.repositories.impl;

import com.social.enums.Action;
import com.social.pojo.SubComment;
import com.social.pojo.SubCommentAction;
import com.social.repositories.SubCommentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LENOVO
 */
@Repository
public class SubCommentRepositoryImpl implements SubCommentRepository {

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
    public SubComment getById(Integer id) {
        Session s = getSession();
        try {
            return s.get(SubComment.class, id);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public List<SubComment> getRepliesByCommentId(int commentId) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SubComment> criteriaQuery = criteriaBuilder.createQuery(SubComment.class);

        Root<SubComment> subCommentRoot = criteriaQuery.from(SubComment.class);
        // WHERE
        List<Predicate> predicates = new ArrayList<>();
        Predicate commentIdPredicate = criteriaBuilder.equal(subCommentRoot.get("comment"), commentId);
        predicates.add(commentIdPredicate);
        //
        criteriaQuery.select(subCommentRoot).where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public long countReplyActionById(int replyId) {
        return 0;
    }

    @Override
    public SubComment save(SubComment subComment) {
        Session session = getSession();
        try {
            session.save(subComment);
            return subComment;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public SubCommentAction save(SubCommentAction subCommentAction) {
        Session session = getSession();
        try {
            session.save(subCommentAction);
            return subCommentAction;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public SubComment update(SubComment subComment) {
        Session session = getSession();
        try {
            session.update(subComment);
            return subComment;
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(SubComment subComment) {
        Session s = getSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        // DELETE 
        CriteriaDelete<SubCommentAction> deleteSubComment
                = criteriaBuilder.createCriteriaDelete(SubCommentAction.class);
        // FROM sub_comment_action
        Root<SubCommentAction> subCommentActionRoot
                = deleteSubComment.from(SubCommentAction.class);
        try {
            // WHERE sub_comment_id = ?
            deleteSubComment.where(
                    criteriaBuilder.equal(
                            subCommentActionRoot.get("subComment"), subComment.getId()
                    )
            );

            int deletedCount = s.createQuery(deleteSubComment).executeUpdate();
            s.delete(subComment);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer subCommentId) {
        return this.delete(getById(subCommentId));
    }

    @Override
    public List<SubComment> getReplies(Map<String, String> params) {
        Session session = getSession();
        Integer size = env.getProperty("PAGINATION", Integer.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SubComment> subCommentRoot = criteriaQuery.from(SubComment.class);
        List<Predicate> predicates = new ArrayList<>();
        String page = (String) params.get("page");
        if (!params.isEmpty()) {
            String commentId = params.get("commentId");
            if (commentId != null && !commentId.isBlank()) {
                predicates.add(
                        criteriaBuilder.equal(subCommentRoot.get("comment"), Integer.valueOf(commentId)));
            }
            if (params.containsKey("alumniId")) {
                String alumniId = (String) params.get("alumniId");
                Subquery<Action> actionSubquery = criteriaQuery.subquery(Action.class);
                Root<SubCommentAction> subCommentActionRoot = actionSubquery.from(SubCommentAction.class);
                actionSubquery.select(subCommentActionRoot.get("action"));
                actionSubquery.where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(subCommentActionRoot.get("user").get("alumniId"), alumniId),
                                criteriaBuilder.equal(subCommentActionRoot.get("subComment"), subCommentRoot)
                        )
                );
                criteriaQuery.multiselect(
                        subCommentRoot, actionSubquery.getSelection());
            } else {
                criteriaQuery.multiselect(subCommentRoot);
            }
            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }

        criteriaQuery.orderBy(criteriaBuilder.desc(subCommentRoot.get("createdDate")));

        Query query = session.createQuery(criteriaQuery);
        query.setMaxResults(size);
        query.setFirstResult((Integer.parseInt(page) - 1) * size);

        if (!params.containsKey("alumniId")) {
            return query.getResultList();
        }

        List<Object[]> resultList = query.getResultList();

        List<SubComment> commentsWithCounts = new ArrayList<>();
        for (Object[] result : resultList) {
            SubComment reply = (SubComment) result[0];
            if (!params.isEmpty() && params.containsKey("alumniId")) {
                reply.setCurrentAction((Action) result[1]);
            }
            commentsWithCounts.add(reply);
        }
        return commentsWithCounts;
    }

    @Override
    public List<Integer> getSubCommentIdsByComment(Integer commentId) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);

        Root<SubComment> subCommentRoot = criteriaQuery.from(SubComment.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                criteriaBuilder.equal(subCommentRoot.get("comment"), commentId));
        criteriaQuery.select(subCommentRoot.get("id"));
        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
