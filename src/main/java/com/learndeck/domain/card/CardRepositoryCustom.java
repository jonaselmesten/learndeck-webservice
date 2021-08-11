package com.learndeck.domain.card;


import java.util.List;

public interface CardRepositoryCustom {
    List<Card> getCardsFromCourse(Long courseId);
}
