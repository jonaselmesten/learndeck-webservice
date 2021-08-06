package com.learndeck.domain.course;

import com.learndeck.domain.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_has_course")
public class UserCourse implements Serializable {

    @Column(name="user_id")
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long userId;
    @Column(name="course_id")
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long courseId;

    public UserCourse() {}

    public UserCourse(long userId, long courseId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public long getCourseId() {
        return courseId;
    }

    public long getUserId() {
        return userId;
    }

    public String getCourseName() {
        return "Course name";
    }
}
