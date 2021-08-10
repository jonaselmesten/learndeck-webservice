package com.learndeck.domain.card;

import com.learndeck.domain.study.StudyCard;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class CardRepositoryImpl implements CardRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Card> getCardsFromClass(Long courseId) {

        Query query = entityManager
                .createNativeQuery("SELECT * FROM db.card WHERE course_id = ?", Card.class);
        query.setParameter(1, courseId);

        return query.getResultList();
    }

    @Override
    public List<StudyCard> getReviews(Long userId, Long courseId) {

        Query query = entityManager
                .createNativeQuery("SELECT * FROM db.card_review  AS CR " +
                        "INNER JOIN db.card AS C ON C.card_id = CR.card_id " +
                        "WHERE user_id = ? AND CR.course_id = ? ", "reviewMapping");

        query.setParameter(1, userId);
        query.setParameter(2, courseId);

        return query.getResultList();
    }

}
