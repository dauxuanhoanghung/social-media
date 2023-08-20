package com.social.repositories.impl;

import com.social.pojo.Comment;
import com.social.pojo.CommentAction;
import com.social.pojo.SubComment;
import com.social.pojo.SubCommentAction;
import com.social.pojo.User;
import com.social.repositories.CommentRepository;
import com.social.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

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
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUserByAlumniId(authentication.getName()).get();
            subCommentAction.setUser(user);
            session.save(subCommentAction);
            return subCommentAction;
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
    public SubComment update(SubComment subComment) {
        Session session = getSession();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUserByAlumniId(authentication.getName()).get();
            return subComment;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public long countActionById(int commentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long countReplyActionById(int replyId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Comment comment) {
        Session s = getSession();
        try {
            s.delete(comment);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean delete(SubComment subComment) {
        Session s = getSession();
        try {
            s.delete(subComment);
            return true;
        } catch (HibernateException ex) {
            return false;
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
    public SubComment getSubCommentById(Integer id) {
        Session s = getSession();
        try {
            return s.get(SubComment.class, id);
        } catch (HibernateException ex) {
            return null;
        }
    }

}
