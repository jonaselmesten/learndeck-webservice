package com.learndeck.domain.card;


import com.learndeck.domain.study.StudyCard;

import java.util.Arrays;
import java.util.List;

public interface CardRepositoryCustom {
    List<Card> getCardsFromClass(Long courseId);

    List<StudyCard> getReviews(Long userId, Long courseId);
}
