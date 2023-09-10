package com.social.repositories.impl;

import com.social.dto.request.PostRequest;
import com.social.enums.Action;
import com.social.pojo.Comment;
import com.social.pojo.Post;
import com.social.pojo.PostAction;
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
import javax.persistence.criteria.Subquery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
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
    public List<Post> getPosts(Map<String, String> params) {
        Session session = getSession();
        Integer size = env.getProperty("PAGINATION", Integer.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Post> postRoot = criteriaQuery.from(Post.class);

        Subquery<Long> countCommentQuery = criteriaQuery.subquery(Long.class);
        Root<Comment> commentRoot = countCommentQuery.from(Comment.class);
        Subquery<Long> countActionQuery = criteriaQuery.subquery(Long.class);
        Root<PostAction> postARoot = countActionQuery.from(PostAction.class);
        countCommentQuery.select(criteriaBuilder.count(commentRoot.get("id")))
                .where(criteriaBuilder.equal(commentRoot.get("postId"), postRoot.get("id")));
        countActionQuery.select(criteriaBuilder.count(postARoot.get("id")))
                .where(criteriaBuilder.equal(postARoot.get("post"), postRoot.get("id")));
        List<Predicate> predicates = new ArrayList<>();
        if (params != null) {
            if (params.containsKey("slug")) {
                String slug = params.get("slug");
                predicates.add(criteriaBuilder.equal(
                        postRoot.get("user").get("slug"),
                        Integer.valueOf(slug))
                );
            } else if (params.containsKey("userId")) {
                String userId = params.get("userId");
                predicates.add(criteriaBuilder.equal(
                        postRoot.get("user").get("id"),
                        Integer.valueOf(userId))
                );
            }
//            String toDate = params.get("toDate");
//            if (fromDate != null && !fromDate.isEmpty()) {
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(userRoot.get("createdDate"),
//                        LocalDateTime.parse(toDate, dateTimeFormatter)));
//            }  
            String alumniId = (String) params.get("alumniId");
            if (alumniId != null && !alumniId.isBlank()) {
                Subquery<Action> subquery = criteriaQuery.subquery(Action.class);
                Root<PostAction> postActionRoot = subquery.from(PostAction.class);
                subquery.select(postActionRoot.get("action"));
                subquery.where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(postActionRoot.get("user").get("alumniId"), alumniId),
                                criteriaBuilder.equal(postActionRoot.get("post"), postRoot)
                        )
                );
                criteriaQuery.multiselect(postRoot,
                        countCommentQuery.getSelection(),
                        countActionQuery.getSelection(),
                        subquery.getSelection());
            } else {
                criteriaQuery.multiselect(postRoot,
                        countCommentQuery.getSelection(),
                        countActionQuery.getSelection());
            }
        }

        criteriaQuery.where(predicates.toArray(Predicate[]::new));
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

        List<Post> result = new ArrayList<>();
        List<Object[]> resultPosts = query.getResultList();
        for (Object[] object : resultPosts) {
            Post p = (Post) object[0];
            p.setCountComment((Long) object[1]);
            p.setCountAction((Long) object[2]);
            if (params != null && params.containsKey("alumniId")) {
                p.setCurrentAction((Action) object[3]);
            }

            result.add(p);
        }
        return result;
    }

    @Override
    public Optional<Post> getPostById(Integer id) {
        Session session = getSession();

        try {
            Post post = session.get(Post.class,
                    id);
            return Optional.ofNullable(post);
        } catch (NoResultException ex) {
            // If no result is found, return an empty Optional
            return Optional.empty();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Post save(Post post) {
        Session s = getSession();
        try {
            s.save(post);
            return post;
        } catch (HibernateException ex) {
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
            return null;
        }
    }


    @Override
    public boolean deleteById(Integer id) {
        Session session = getSession();
        Post post = session.get(Post.class, id);
        try {
            if (post != null) {
                session.delete(post);
                return true;
            }
            return false;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public boolean delete(Post post) {
        Session session = getSession();
        try {
            session.delete(post);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

}
