package com.learndeck.domain.course;

import com.learndeck.web.CardController;
import com.learndeck.web.CourseController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CourseModelAssembler implements RepresentationModelAssembler<Course, EntityModel<Course>> {

    @Override
    public EntityModel<Course> toModel(Course course) {
        return EntityModel.of(course,
                linkTo(methodOn(CourseController.class).one(course.getCourseId())).withSelfRel(),
                linkTo(methodOn(CourseController.class).all()).withRel("courses"),
                linkTo(methodOn(CardController.class).allCourse(course.getCourseId())).withRel("cards"));
    }
}
