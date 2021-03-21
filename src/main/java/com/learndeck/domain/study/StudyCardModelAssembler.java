package com.learndeck.domain.study;

import com.learndeck.web.UserCourseController;
import com.learndeck.web.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudyCardModelAssembler implements RepresentationModelAssembler<StudyCard, EntityModel<StudyCard>> {

    @Override
    public EntityModel<StudyCard> toModel(StudyCard user) {
        return EntityModel.of(user);

    }
}
