package com.learndeck.domain.course;

import javax.persistence.*;
import java.io.Serializable;


@SqlResultSetMapping(name="userCourseMapping",
        classes={
                @ConstructorResult(
                        targetClass= UserCourse.class,
                        columns={
                                @ColumnResult(name="course_name", type= String.class),
                                @ColumnResult(name="due_count", type= Integer.class)
                        }
                )
        }
)

@Entity
public class UserCourse implements Serializable {

    @Id @Column(name = "course_id", nullable = false)
    private long courseId;
    @Id @Column(name = "user_id", nullable = false)
    private long userId;
    private String courseName;
    private int dueCount;

    public UserCourse() {}

    public UserCourse(long courseId, long userId, String courseName, int due_count) {
        this.courseId = courseId;
        this.userId = userId;
        this.courseName = courseName;
        this.dueCount = due_count;
    }

    public long getCourseId() { return courseId; }

    public String getCourseName() { return courseName; }

    public long getUserId() { return userId; }

    public int getDueCount() {
        return dueCount;
    }
}
