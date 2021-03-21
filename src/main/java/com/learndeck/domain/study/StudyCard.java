package com.learndeck.domain.study;


import java.io.Serializable;
import java.sql.Date;

public class StudyCard implements Serializable {

    private Long cardId;
    private Long courseId;
    private Long userId;
    private Double difficulty;
    private Date nextReview;
    private Integer veryEasy;
    private Integer easy;
    private Integer medium;
    private Integer hard;

    public StudyCard(Long cardId, Long courseId, Long userId, Double difficulty, String nextReview, Integer veryEasy, Integer easy, Integer medium, Integer hard) {
        this.cardId = cardId;
        this.courseId = courseId;
        this.userId = userId;
        this.difficulty = difficulty;
        this.nextReview = Date.valueOf(nextReview);
        this.veryEasy = veryEasy;
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
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

    public Integer getEasy() {
        return easy;
    }

    public Integer getHard() {
        return hard;
    }

    public Integer getMedium() {
        return medium;
    }

    public Integer getVeryEasy() {
        return veryEasy;
    }

    public Long getCourseId() {
        return courseId;
    }
}
