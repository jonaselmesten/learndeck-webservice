package com.learndeck.domain.course;

import java.util.List;

public interface CourseRepositoryCustom {
    List<UserCourse> getStudentCourses(Long userId);
}
