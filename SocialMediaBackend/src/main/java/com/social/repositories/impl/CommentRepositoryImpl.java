package com.social.repositories.impl;

import com.social.pojo.Comment;
import com.social.pojo.CommentAction;
import com.social.pojo.SubComment;
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
import org.modelmapper.ModelMapper;
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
@PropertySource("classpath:application.properties")
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
    public Long countActionById(int commentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        Session s = getSession();
        try {
            // DELETE Sub
            List<SubComment> subs = this.subCommentRepository.getRepliesByCommentId(comment.getId());
            for (SubComment sub : subs) {
                this.subCommentRepository.delete(sub);
            }

            // DELETE Action in Comment
            CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
            // DELETE 
            CriteriaDelete<CommentAction> deleteComment
                    = criteriaBuilder.createCriteriaDelete(CommentAction.class);
            // FROM sub_comment_action
            Root<CommentAction> subCommentActionRoot = deleteComment.from(CommentAction.class);
            // WHERE sub_comment_id = ?
            deleteComment.where(
                    criteriaBuilder.equal(
                            subCommentActionRoot.get("commentId"), comment.getId()
                    )
            );

            int deletedCount = s.createQuery(deleteComment).executeUpdate();

            // DELETE comment Object after all
            s.delete(comment);
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
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> commentRoot = criteriaQuery.from(Comment.class);
        List<Predicate> predicates = new ArrayList<>();
        String page = (String) params.get("page");
        if (!params.isEmpty()) {
            String postId = params.get("postId");
            if (postId != null && postId.isBlank()) {
                predicates.add(
                        criteriaBuilder.equal(commentRoot.get("postId"), Integer.valueOf(postId)));
            }

            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }
        
        criteriaQuery.orderBy(criteriaBuilder.desc(commentRoot.get("createdDate")));

        Query query = session.createQuery(criteriaQuery);
        query.setMaxResults(size);
        query.setFirstResult((Integer.parseInt(page) - 1) * size);

        return query.getResultList();
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
