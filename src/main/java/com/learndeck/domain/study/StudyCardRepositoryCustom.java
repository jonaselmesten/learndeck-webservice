package com.learndeck.domain.study;

import java.util.List;

public interface StudyCardRepositoryCustom {

    List<StudyCard> getReviews(Long userId, Long courseId);
}
