package com.learndeck.domain.study;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CardReviewRepositoryImpl implements CardReviewRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CardReview> getReviews(Long userId, Long courseId) {

        Query query = entityManager
                .createNativeQuery("SELECT * FROM db.card_review  AS CR " +
                        "INNER JOIN db.card AS C ON C.card_id = CR.card_id " +
                        "WHERE user_id = ? AND CR.course_id = ? ", "reviewMapping");

        query.setParameter(1, userId);
        query.setParameter(2, courseId);

        return query.getResultList();
    }

    @Override
    public List<CardReview> getStudyCards(Long userId, Long courseId) {

        Query query = entityManager
                .createNativeQuery("SELECT " +
                        "review_id, " +
                        "C.question, " +
                        "C.answer " +
                        "FROM db.card  AS C " +
                        "INNER JOIN db.card_review AS CR ON C.card_id = CR.card_id " +
                        "WHERE user_id = ? AND CR.course_id = ? ", "studyCardMapping");

        query.setParameter(1, userId);
        query.setParameter(2, courseId);

        return query.getResultList();
    }

}
