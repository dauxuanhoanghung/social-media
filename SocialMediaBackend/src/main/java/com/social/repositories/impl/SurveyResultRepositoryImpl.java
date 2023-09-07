package com.social.repositories.impl;

import com.social.pojo.Post;
import com.social.pojo.Question;
import com.social.pojo.SurveyResult;
import com.social.pojo.User;
import com.social.repositories.SurveyResultRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyResultRepositoryImpl implements SurveyResultRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    private Session getSession() {
        SessionFactory sessionFactory = this.sessionFactory.getObject();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    @Override
    public List<Object[]> getResultSurvey(Map<String, String> params) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SurveyResult> resultRoot = criteriaQuery.from(SurveyResult.class);
        Root<Question> questionRoot = criteriaQuery.from(Question.class);
        Root<Post> postRoot = criteriaQuery.from(Post.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        // WHERE post.id == question.post_id
        predicates.add(criteriaBuilder.equal(postRoot.get("id"), questionRoot.get("post")));
        // WHERE result.question_id == question.id
        predicates.add(criteriaBuilder.equal(resultRoot.get("questionId"), questionRoot.get("id")));
        // WHERE result.question_id == user.id
        predicates.add(criteriaBuilder.equal(resultRoot.get("userId"), userRoot.get("id")));

        if (params != null && !params.isEmpty()) {
            // WHERE postId == :postId
            if (params.containsKey("postId")) {
                Integer postId = Integer.valueOf(params.get("postId"));
                predicates.add(criteriaBuilder.equal(postRoot.get("id"), postId));
            }

        }
        criteriaQuery.multiselect(
                userRoot.get("alumniId"),
                questionRoot.get("content"),
                resultRoot.get("result")
        );
        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
