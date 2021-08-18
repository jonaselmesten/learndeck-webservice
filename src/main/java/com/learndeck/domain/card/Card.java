package com.learndeck.domain.card;

import com.learndeck.domain.study.CardReview;

import javax.persistence.*;

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
