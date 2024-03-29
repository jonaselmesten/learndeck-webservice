package com.learndeck.domain.course;

import com.learndeck.web.StudyCardController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserCourseModelAssembler implements RepresentationModelAssembler<UserCourse, EntityModel<UserCourse>> {

    @Override
    public EntityModel<UserCourse> toModel(UserCourse course) {
        return EntityModel.of(course,
                linkTo(methodOn(StudyCardController.class).getUserReviews(course.getUserId(), course.getCourseId())).withRel("study"));
    }

}
