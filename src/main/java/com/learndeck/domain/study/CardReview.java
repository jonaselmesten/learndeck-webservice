package com.learndeck.domain.study;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@SqlResultSetMapping(name="reviewMapping",
        classes={
                @ConstructorResult(
                        targetClass= CardReview.class,
                        columns={
                                @ColumnResult(name="review_id", type=Long.class),
                                @ColumnResult(name="next_review_date", type= String.class),
                                @ColumnResult(name="date_modifier", type=Integer.class),
                                @ColumnResult(name="button_stats", type=String.class),
                                @ColumnResult(name="question_type", type=String.class),
                                @ColumnResult(name="question", type=String.class),
                                @ColumnResult(name="answer_type", type=String.class),
                                @ColumnResult(name="answer", type=String.class)
                        }
                )
        }
)

@Entity(name = "card_review")
public class CardReview implements Serializable {

    @Column(name="review_id")
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) Long reviewId;
    @Column(name="next_review_date")
    private Date nextReview;
    @Column(name="date_modifier")
    private Integer dateModifier;
    @Column(name="button_stats")
    private String buttonStats;

    private String questionType;
    private String question;
    private String answerType;
    private String answer;

    public CardReview() {}

    public CardReview(Long reviewId, String nextReview, Integer dateModifier, String buttonStats, String questionType, String question, String answerType, String answer) {
        this.reviewId = reviewId;
        this.nextReview = Date.valueOf(nextReview);
        this.dateModifier = dateModifier;
        this.buttonStats = buttonStats;
        this.questionType = questionType;
        this.question = question;
        this.answerType = answerType;
        this.answer = answer;
    }

    public CardReview(Double difficulty, String nextReview, Integer dateModifier) {
        this.nextReview = Date.valueOf(nextReview);
        this.dateModifier = dateModifier;
    }

    public CardReview(Long reviewId, String question, String answer) {
        this.reviewId = reviewId;
        this.question = question;
        this.answer = answer;
    }

    public String getButtonStats() {
        return buttonStats;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public void setButtonStats(String buttonStats) {
        this.buttonStats = buttonStats;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnswerType() {
        return answerType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public Date getNextReview() {
        return nextReview;
    }

    public Integer getDateModifier() {
        return dateModifier;
    }

    public Long getReviewId() { return reviewId; }

    public void setDateModifier(Integer dateModifier) {
        this.dateModifier = dateModifier;
    }

    public void setNextReview(Date nextReview) {
        this.nextReview = nextReview;
    }
}
