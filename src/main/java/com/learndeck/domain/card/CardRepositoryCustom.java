package com.learndeck.domain.card;


import java.util.List;

public interface CardRepositoryCustom {
    List<Card> getCardsFromClass(Long courseId);
}
