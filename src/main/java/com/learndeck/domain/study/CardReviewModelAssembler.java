package com.learndeck.domain.study;

import com.learndeck.web.StudyCardController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardReviewModelAssembler implements RepresentationModelAssembler<CardReview, EntityModel<CardReview>> {

    @Override
    public EntityModel<CardReview> toModel(CardReview studyCard) {
        return EntityModel.of(studyCard,
                linkTo(methodOn(StudyCardController.class).one(studyCard.getReviewId())).withSelfRel(),
                linkTo(methodOn(StudyCardController.class).all()).withRel("all"));
    }
}
