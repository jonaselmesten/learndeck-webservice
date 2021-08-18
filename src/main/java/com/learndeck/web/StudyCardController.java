package com.learndeck.web;

import com.learndeck.domain.card.CardNotFoundException;
import com.learndeck.domain.study.CardReview;
import com.learndeck.domain.study.CardReviewModelAssembler;
import com.learndeck.domain.study.CardReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StudyCardController {

    @Autowired
    private CardReviewRepository repository;

    private final CardReviewModelAssembler assembler;

    public StudyCardController(CardReviewModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/reviews/users")
    public CollectionModel<EntityModel<CardReview>> getUserReviews(@RequestParam(name = "user_id") Long userId,
                                                                   @RequestParam(name = "course_id") Long courseId) {

        List<EntityModel<CardReview>> cards = repository.getReviews(userId, courseId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(StudyCardController.class).getUserReviews(userId, courseId)).withSelfRel());
    }

    @GetMapping("/reviews/cards")
    public CollectionModel<EntityModel<CardReview>> getUserStudyCards(@RequestParam(name = "user_id") Long userId,
                                                                      @RequestParam(name = "course_id") Long courseId) {

        List<EntityModel<CardReview>> cards = repository.getStudyCards(userId, courseId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(StudyCardController.class).getUserStudyCards(userId, courseId)).withSelfRel());
    }

    @PutMapping(value = "/reviews/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id,
                                          @RequestParam Double difficulty,
                                          @RequestParam(name = "date") String nextReview,
                                          @RequestParam(name = "modifier") Integer dateModifier) {

        Optional<CardReview> updatedReview = repository.findById(id)
                .map(review -> {
                    review.setNextReview(Date.valueOf(nextReview));
                    review.setDateModifier(dateModifier);
                    return repository.save(review);});

        if(updatedReview.isPresent()) {
            EntityModel<CardReview> entityModel = assembler.toModel(updatedReview.get());
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/reviews/{id}")
    public EntityModel<CardReview> one(@PathVariable Long id) {

        CardReview studyCard = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));

        return assembler.toModel(studyCard);
    }

    @GetMapping("/reviews")
    public CollectionModel<EntityModel<CardReview>> all(){

        List<EntityModel<CardReview>> studyCards = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(studyCards,
                linkTo(methodOn(StudyCardController.class).all()).withSelfRel());
    }
}
