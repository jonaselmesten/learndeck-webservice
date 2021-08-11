package com.learndeck.web;

import com.learndeck.domain.card.CardNotFoundException;
import com.learndeck.domain.study.StudyCard;
import com.learndeck.domain.study.StudyCardModelAssembler;
import com.learndeck.domain.study.StudyCardRepository;
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
    private StudyCardRepository repository;

    private final StudyCardModelAssembler assembler;

    public StudyCardController(StudyCardModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/reviews/users")
    public CollectionModel<EntityModel<StudyCard>> getUserReviews(@RequestParam(name = "user_id") Long userId,
                                                                  @RequestParam(name = "course_id") Long courseId) {

        List<EntityModel<StudyCard>> cards = repository.getReviews(userId, courseId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(StudyCardController.class).getUserReviews(userId, courseId)).withSelfRel());
    }

    @PutMapping(value = "/reviews/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id,
                                          @RequestParam Double difficulty,
                                          @RequestParam(name = "date") String nextReview,
                                          @RequestParam(name = "modifier") Integer dateModifier) {

        Optional<StudyCard> updatedReview = repository.findById(id)
                .map(review -> {
                    review.setDifficulty(difficulty);
                    review.setNextReview(Date.valueOf(nextReview));
                    review.setDateModifier(dateModifier);
                    return repository.save(review);});

        if(updatedReview.isPresent()) {
            EntityModel<StudyCard> entityModel = assembler.toModel(updatedReview.get());
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/reviews/{id}")
    public EntityModel<StudyCard> one(@PathVariable Long id) {

        StudyCard studyCard = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));

        return assembler.toModel(studyCard);
    }

    @GetMapping("/reviews")
    public CollectionModel<EntityModel<StudyCard>> all(){

        List<EntityModel<StudyCard>> studyCards = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(studyCards,
                linkTo(methodOn(StudyCardController.class).all()).withSelfRel());
    }
}
