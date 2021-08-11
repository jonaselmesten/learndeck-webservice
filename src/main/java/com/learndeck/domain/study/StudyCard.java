package com.learndeck.domain.study;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "card_review")
public class StudyCard implements Serializable {

    @Column(name="review_id")
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) Long reviewId;
    private Long cardId;
    private Long courseId;
    private Long userId;
    @Column(name="general_difficulty")
    private Double difficulty;
    @Column(name="next_review_date")
    private Date nextReview;
    @Column(name="date_modifier")
    private Integer dateModifier;

    public StudyCard() {}

    public StudyCard(Long reviewId, Long cardId, Long courseId, Long userId, Double difficulty, String nextReview, Integer dateModifier) {
        this.reviewId = reviewId;
        this.cardId = cardId;
        this.courseId = courseId;
        this.userId = userId;
        this.difficulty = difficulty;
        this.nextReview = Date.valueOf(nextReview);
        this.dateModifier = dateModifier;
    }

    public StudyCard(Double difficulty, String nextReview, Integer dateModifier) {
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

    public Long getReviewId() {
        return reviewId;
    }

    public void setDateModifier(Integer dateModifier) {
        this.dateModifier = dateModifier;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public void setNextReview(Date nextReview) {
        this.nextReview = nextReview;
    }
}
