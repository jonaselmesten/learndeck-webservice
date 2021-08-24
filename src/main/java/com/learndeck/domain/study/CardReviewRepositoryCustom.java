package com.learndeck.domain.study;

import java.util.List;

public interface CardReviewRepositoryCustom {

    List<CardReview> getReviews(Long userId, Long courseId);

}
