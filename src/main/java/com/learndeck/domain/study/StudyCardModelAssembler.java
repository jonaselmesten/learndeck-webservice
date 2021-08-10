package com.learndeck.domain.study;

import com.learndeck.web.CardController;
import com.learndeck.web.StudyCardController;
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
    public EntityModel<StudyCard> toModel(StudyCard card) {
        return EntityModel.of(card,
                linkTo(methodOn(StudyCardController.class).studyCard(card.getUserId(), card.getCourseId(), Difficulty.HARD.value)).withRel("hard"),
                linkTo(methodOn(StudyCardController.class).studyCard(card.getUserId(), card.getCourseId(), Difficulty.MEDIUM.value)).withRel("medium"),
                linkTo(methodOn(StudyCardController.class).studyCard(card.getUserId(), card.getCourseId(), Difficulty.EASY.value)).withRel("easy"),
                linkTo(methodOn(StudyCardController.class).studyCard(card.getUserId(), card.getCourseId(), Difficulty.VERY_EASY.value)).withRel("very_easy")
                );
    }
}
