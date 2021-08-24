package com.learndeck.web;

import com.learndeck.domain.card.CardNotFoundException;
import com.learndeck.domain.study.CardReview;
import com.learndeck.domain.study.CardReviewModelAssembler;
import com.learndeck.domain.study.CardReviewRepository;
import com.learndeck.domain.study.CardReviewRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
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

    /**
     * Get all card reviews from a specific course to a specific user.
     */
    @RequestMapping("/courses/{id1}/reviews/users/{id2}")
    public CollectionModel<EntityModel<CardReview>> getUserReviews(@PathVariable(name = "id1") Long courseId,
                                                                   @PathVariable(name = "id2") Long userId) {

        List<EntityModel<CardReview>> cards = repository.getReviews(userId, courseId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(StudyCardController.class).getUserReviews(userId, courseId)).withSelfRel());
    }

    /**
     * Updates a specific review.
     * @param id Review id.
     * @param reviewRequest New review data.
     * @return The updated review.
     */
    @PutMapping(value = "/courses/reviews/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateReview(@PathVariable Long id,
                                          @RequestBody CardReviewRequest reviewRequest) {

        System.out.println(reviewRequest.getButtonStats());
        System.out.println(reviewRequest.getNextReview());
        System.out.println(reviewRequest.getDateModifier());

        Optional<CardReview> updatedReview = repository.findById(id)
                .map(review -> {
                    review.setButtonStats(reviewRequest.getButtonStats());
                    review.setNextReview(Date.valueOf(reviewRequest.getNextReview()));
                    review.setDateModifier(reviewRequest.getDateModifier());
                    return repository.save(review);});

        if(updatedReview.isPresent()) {
            EntityModel<CardReview> entityModel = assembler.toModel(updatedReview.get());
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }else
            return ResponseEntity.notFound().build();
    }

    /**
     * Get a specific review.
     */
    @GetMapping("/courses/reviews/{id}")
    public EntityModel<CardReview> one(@PathVariable Long id) {

        CardReview studyCard = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));

        return assembler.toModel(studyCard);
    }

    /**
     * Get all the reviews.
     */
    @GetMapping("/courses/reviews")
    public CollectionModel<EntityModel<CardReview>> all(){

        List<EntityModel<CardReview>> studyCards = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(studyCards,
                linkTo(methodOn(StudyCardController.class).all()).withSelfRel());
    }
}
