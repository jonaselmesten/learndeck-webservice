package com.learndeck.web;

import com.learndeck.domain.course.*;
import com.learndeck.domain.study.StudyCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserCourseController {

    @Autowired
    private CourseRepository repository;

    private final UserCourseModelAssembler assembler;

    public UserCourseController(UserCourseModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/user/{id}/courses")
    public CollectionModel<EntityModel<UserCourse>> studentCourses(@PathVariable Long id) {

        List<EntityModel<UserCourse>> courses = repository.getStudentCourses(id).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(courses,
                linkTo(methodOn(UserCourseController.class).studentCourses(id)).withSelfRel());
    }



}
