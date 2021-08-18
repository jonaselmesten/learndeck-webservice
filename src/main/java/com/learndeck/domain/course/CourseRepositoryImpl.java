package com.learndeck.domain.course;

import com.learndeck.domain.card.Card;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CourseRepositoryImpl implements CourseRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserCourse> getStudentCourses(Long userId) {

        Query query = entityManager
                .createNativeQuery("SELECT C.course_id, SHC.user_id, C.course_name, " +
                        "(SELECT COUNT(*) FROM db.card_review AS due_count " +
                        "WHERE course_id = C.course_id AND user_id = ? AND next_review_date <= curdate()) AS due_count " +
                        "FROM db.course AS C " +
                        "INNER JOIN db.student_has_course AS SHC ON C.course_id = SHC.course_id " +
                        "WHERE SHC.user_id = ? ", UserCourse.class);
        query.setParameter(1, userId);
        query.setParameter(2, userId);

        return query.getResultList();
    }
}
