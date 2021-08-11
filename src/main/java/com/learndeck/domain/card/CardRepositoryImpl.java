package com.learndeck.domain.card;

import com.learndeck.domain.study.StudyCard;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class CardRepositoryImpl implements CardRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Card> getCardsFromCourse(Long courseId) {

        Query query = entityManager
                .createNativeQuery("SELECT * FROM db.card WHERE course_id = ?", Card.class);
        query.setParameter(1, courseId);

        return query.getResultList();
    }

}
