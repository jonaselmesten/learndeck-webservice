package com.learndeck.domain.course;

import javax.persistence.*;

@Entity
public class Course {

    @Column(name="course_id")
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long courseId;
    @Column(name="made_by_teacher")
    private long teacherId;
    @Column(name="course_name")
    private String courseName;

    public Course() {}

    public Course(long courseId, long teacherId, String courseName) {
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.courseName = courseName;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public long getTeacherId() {
        return teacherId;
    }
}
