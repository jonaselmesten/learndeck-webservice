package com.learndeck.domain.study;


import java.io.Serializable;
import java.sql.Date;

public class StudyCard implements Serializable {

    private Long cardId;
    private Long courseId;
    private Long userId;
    private Double difficulty;
    private Date nextReview;
    private Integer dateModifier;

    public StudyCard(Long cardId, Long courseId, Long userId, Double difficulty, String nextReview, Integer dateModifier) {
        this.cardId = cardId;
        this.courseId = courseId;
        this.userId = userId;
        this.difficulty = difficulty;
        this.nextReview = Date.valueOf(nextReview);
        this.dateModifier = dateModifier;
    }

    public Long getCardId() {
        return cardId;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getNextReview() {
        return nextReview;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public Integer getDateModifier() {
        return dateModifier;
    }

    public Long getCourseId() {
        return courseId;
    }
}
