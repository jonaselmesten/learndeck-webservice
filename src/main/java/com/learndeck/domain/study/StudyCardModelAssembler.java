package com.learndeck.domain.study;

import com.learndeck.domain.card.Card;
import com.learndeck.web.CardController;
import com.learndeck.web.StudyCardController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudyCardModelAssembler implements RepresentationModelAssembler<StudyCard, EntityModel<StudyCard>> {

    @Override
    public EntityModel<StudyCard> toModel(StudyCard studyCard) {
        return EntityModel.of(studyCard,
                linkTo(methodOn(StudyCardController.class).one(studyCard.getReviewId())).withSelfRel(),
                linkTo(methodOn(StudyCardController.class).all()).withRel("all"));
    }
}
