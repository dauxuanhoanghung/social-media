package com.social.repositories.impl;

import com.social.pojo.ImagePost;
import com.social.repositories.ImagePostRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class ImagePostRepositoryImpl implements ImagePostRepository {

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
    public ImagePost save(ImagePost imagePost) {
        Session session = getSession();

        try {
            session.save(imagePost);
            return imagePost;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(String url) {
        return false;
    }

    @Override
    public List<String> getImageUrls(Map<String, String> params) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

        Root<ImagePost> imageRoot = criteriaQuery.from(ImagePost.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!params.isEmpty()) {
            // comment.post_id == :postId
            String postId = params.get("postId");
            if (postId != null && !postId.isBlank()) {
                predicates.add(
                        criteriaBuilder.equal(imageRoot.get("postId"), Integer.valueOf(postId)));
            }

            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }
        criteriaQuery.select(imageRoot.get("url"));
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();

    }
}
