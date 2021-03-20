package com.learndeck.web;

import com.learndeck.domain.card.Card;
import com.learndeck.domain.card.CardNotFoundException;
import com.learndeck.domain.course.Course;
import com.learndeck.domain.course.CourseModelAssembler;
import com.learndeck.domain.course.CourseNotFoundException;
import com.learndeck.domain.course.CourseRepository;
import com.learndeck.domain.user.User;
import com.learndeck.domain.user.UserModelAssembler;
import com.learndeck.domain.user.UserNotFoundException;
import com.learndeck.domain.user.UserRepository;
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
public class CourseController {

    @Autowired
    private CourseRepository repository;

    private final CourseModelAssembler assembler;

    public CourseController(CourseModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/courses")
    public CollectionModel<EntityModel<Course>> all(){

        List<EntityModel<Course>> courses = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(courses,
                linkTo(methodOn(CourseController.class).all()).withSelfRel());
    }

    @GetMapping("/course/{id}")
    public EntityModel<Course> one(@PathVariable Long id) {

        Course course = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return assembler.toModel(course);

    }


}
