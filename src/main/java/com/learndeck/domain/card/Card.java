package com.learndeck.domain.card;

import javax.persistence.*;

@Entity
public class Card {

    @Column(name="card_id")
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long cardId;
    @Column(name="course_id")
    private long courseId;
    @Column(name="general_difficulty")
    private double generalDifficulty;
    private String question;
    private String answer;

    public Card() {}

    public Card(long cardId, long courseId, double generalDifficulty, String question, String answer) {
        this.cardId = cardId;
        this.courseId = courseId;
        this.generalDifficulty = generalDifficulty;
        this.question = question;
        this.answer = answer;
    }

    public double getGeneralDifficulty() {
        return generalDifficulty;
    }

    public Long getCardId() {
        return cardId;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}
