package com.social.repositories.impl;

import com.social.pojo.Comment;
import com.social.pojo.CommentAction;
import com.social.pojo.SubComment;
import com.social.pojo.SubCommentAction;
import com.social.pojo.User;
import com.social.repositories.CommentRepository;
import com.social.repositories.SubCommentRepository;
import com.social.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional
@PropertySource("classpath:/application.properties")
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubCommentRepository subCommentRepository;

    @Autowired
    private Environment env;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public Comment save(Comment comment) {
        Session session = getSession();
        try {
            session.save(comment);
            return comment;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public CommentAction save(CommentAction commentAction) {
        Session session = getSession();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUserByAlumniId(authentication.getName()).get();
            commentAction.setUser(user);
            session.save(commentAction);
            return commentAction;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Comment update(Comment comment) {
        Session session = getSession();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUserByAlumniId(authentication.getName()).get();
            return comment;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Comment getCommentById(Integer id) {
        Session s = getSession();
        try {
            return s.get(Comment.class, id);
        } catch (HibernateException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(Comment comment) {
        Session session = getSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            // GET list id
            List<Integer> subIds = this.subCommentRepository.getSubCommentIdsByComment(comment.getId());
            // Build delete sub comment action
            CriteriaDelete<SubCommentAction> deleteSubAction
                    = criteriaBuilder.createCriteriaDelete(SubCommentAction.class);
            Root<SubCommentAction> subCommentActionRoot = deleteSubAction.from(SubCommentAction.class);
            deleteSubAction.where(
                    subCommentActionRoot.get("subComment").in(subIds)
            );
            // DELETE SubAction
            int deleteSubActionCount = session.createQuery(deleteSubAction).executeUpdate();

            // DELETE Sub
            CriteriaDelete<SubComment> deleteSubComment
                    = criteriaBuilder.createCriteriaDelete(SubComment.class);
            Root<SubComment> subCommentRoot = deleteSubComment.from(SubComment.class);
            deleteSubComment.where(
                    subCommentRoot.get("comment").in(comment.getId())
            );
            int deleteSubCommentCount = session.createQuery(deleteSubComment).executeUpdate();

            // DELETE Action in Comment
            // DELETE 
            CriteriaDelete<CommentAction> deleteComment
                    = criteriaBuilder.createCriteriaDelete(CommentAction.class);
            // FROM comment_action
            Root<CommentAction> commentActionRoot = deleteComment.from(CommentAction.class);
            // WHERE sub_comment_id = ?
            deleteComment.where(
                    criteriaBuilder.equal(
                            commentActionRoot.get("comment"), comment.getId()
                    )
            );

            int deletedCount = session.createQuery(deleteComment).executeUpdate();

            // DELETE comment Object after all
            session.delete(comment);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer commentId) {
        return this.delete(getCommentById(commentId));
    }

    @Override
    public List<Comment> getComments(Map<String, String> params) {
        Session session = getSession();
        Integer size = env.getProperty("PAGINATION", Integer.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Comment> commentRoot = criteriaQuery.from(Comment.class);
        Root<SubComment> replyRoot = criteriaQuery.from(SubComment.class);

        List<Predicate> predicates = new ArrayList<>();
        String page = (String) params.get("page");
        // SELECT comment, count()
        criteriaQuery.multiselect(commentRoot, criteriaBuilder.count(replyRoot.get("id")));
        // comment.id == sub_comment.comment_id
        predicates.add(
                criteriaBuilder.equal(
                        commentRoot.get("id"), replyRoot.get("comment")
                ));
        if (!params.isEmpty()) {
            // comment.post_id == :postId
            String postId = params.get("postId");
            if (postId != null && !postId.isBlank()) {
                predicates.add(
                        criteriaBuilder.equal(commentRoot.get("postId"), Integer.valueOf(postId)));
            }

            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }

        criteriaQuery.orderBy(criteriaBuilder.desc(commentRoot.get("createdDate")));
        criteriaQuery.groupBy(commentRoot.get("id"));

        Query query = session.createQuery(criteriaQuery);
        query.setMaxResults(size);
        query.setFirstResult((Integer.parseInt(page) - 1) * size);
        List<Object[]> resultList = query.getResultList();

        List<Comment> commentsWithCounts = new ArrayList<>();
        for (Object[] result : resultList) {
            Comment comment = (Comment) result[0];
            Long subcommentCount = (Long) result[1];
            comment.setCountReply(subcommentCount);
            commentsWithCounts.add(comment);
        }
        return commentsWithCounts;
    }

    @Override
    public Long countSubCommentById(Integer commentId) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<SubComment> subCommentRoot = query.from(SubComment.class);

        query.select(criteriaBuilder.count(subCommentRoot.get("id")))
                .where(criteriaBuilder.equal(subCommentRoot.get("comment"), commentId));

        return session.createQuery(query).getSingleResult();
    }
}
