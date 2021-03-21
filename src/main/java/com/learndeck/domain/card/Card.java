package com.learndeck.domain.card;

import com.learndeck.domain.study.StudyCard;

import javax.persistence.*;
import java.sql.Date;

@SqlResultSetMapping(name="reviewMapping",
        classes={
                @ConstructorResult(
                        targetClass= StudyCard.class,
                        columns={
                                @ColumnResult(name="card_id", type=Long.class),
                                @ColumnResult(name="course_id", type=Long.class),
                                @ColumnResult(name="user_id", type=Long.class),
                                @ColumnResult(name="general_difficulty", type=Double.class),
                                @ColumnResult(name="next_review_date", type= String.class),
                                @ColumnResult(name="very_easy_pushed", type=Integer.class),
                                @ColumnResult(name="easy_pushed", type=Integer.class),
                                @ColumnResult(name="medium_pushed", type=Integer.class),
                                @ColumnResult(name="hard_pushed", type=Integer.class)
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
