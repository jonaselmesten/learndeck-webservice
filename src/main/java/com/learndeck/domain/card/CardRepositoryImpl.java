package com.learndeck.domain.card;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

}
