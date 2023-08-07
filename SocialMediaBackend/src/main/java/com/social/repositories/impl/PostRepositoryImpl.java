package com.social.repositories.impl;

import com.social.dto.request.AnswerRequest;
import com.social.dto.request.QuestionRequest;
import com.social.dto.request.SurveyRequest;
import com.social.enums.QuestionType;
import com.social.pojo.Choice;
import com.social.pojo.Post;
import com.social.pojo.Question;
import com.social.pojo.User;
import com.social.repositories.PostRepository;
import com.social.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.NoResultException;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional
@PropertySource("classpath:application.properties")
public class PostRepositoryImpl implements PostRepository {

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
    public List<Post> getPosts(Map<String, Object> params) {
        Session session = getSession();
        Integer size = env.getProperty("PAGINATION", Integer.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);

        Root<Post> postRoot = criteriaQuery.from(Post.class);
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
//            String fromDate = params.get("fromDate");
//            if (fromDate != null && !fromDate.isEmpty()) {
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(userRoot.get("createdDate"),
//                        LocalDateTime.parse(fromDate, dateTimeFormatter)));
//            }
//            String toDate = params.get("toDate");
//            if (fromDate != null && !fromDate.isEmpty()) {
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(userRoot.get("createdDate"),
//                        LocalDateTime.parse(toDate, dateTimeFormatter)));
//            }
        }

        criteriaQuery.orderBy(criteriaBuilder.desc(postRoot.get("createdDate")));

        Query query = session.createQuery(criteriaQuery);
        query.setMaxResults(size);
        if (params != null) {
            String page = (String) params.get("page");
            if (page != null && !page.isEmpty()) {
                query.setFirstResult((Integer.parseInt(page) - 1) * size);
            } else {
                query.setFirstResult(0);
            }

        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Post> getPostById(String id) {
        Session session = getSession();
        try {
            Post post = session.get(Post.class, id);
            return Optional.ofNullable(post);
        } catch (NoResultException ex) {
            // If no result is found, return an empty Optional
            return Optional.empty();
        }
    }

    @Override
    public Post save(Post post) {
        Session s = getSession();
        try {
            s.save(post);
            return post;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Post update(Post post) {
        Session s = getSession();
        try {
            s.update(post);
            return post;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deletePost(Integer id) {
        Session session = getSession();
        Post post = session.get(Post.class, id);
        try {
            if (post != null) {
                session.delete(post);
                return true;
            }
            return false;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Post saveSurvey(SurveyRequest surveyRequest) {
        Session session = getSession();
        Post surveyPost = mapper.map(surveyRequest, Post.class);
        surveyPost.setQuestions(null);
        surveyPost.setLockComment(Boolean.FALSE);
        surveyPost.setCountAction(0);
        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUserByAlumniId("2").get();
            surveyPost.setUser(user);
            session.save(surveyPost);

            for (QuestionRequest qr : surveyRequest.getQuestions()) {
                Question question = mapper.map(qr, Question.class);
                question.setPost(surveyPost);
                question.setChoices(null);
                session.save(question);

                if (!question.getQuestionType().equals(QuestionType.TEXT)) {
                    for (AnswerRequest ar : qr.getAnswers()) {
                        Choice choice = mapper.map(ar, Choice.class);
                        choice.setQuestion(question);
                        session.save(choice);
                    }
                }
            }

            return surveyPost;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
