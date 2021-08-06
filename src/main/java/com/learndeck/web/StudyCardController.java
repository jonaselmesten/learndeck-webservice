package com.learndeck.web;

import com.learndeck.domain.card.CardRepository;
import com.learndeck.domain.study.StudyCard;
import com.learndeck.domain.study.StudyCardModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StudyCardController {

    @Autowired
    private CardRepository repository;

    private final StudyCardModelAssembler assembler;

    public StudyCardController(StudyCardModelAssembler assembler) {
        this.assembler = assembler;
    }


    @GetMapping("/user/{userId}/study-course/{courseId}")
    public CollectionModel<EntityModel<StudyCard>> studyCourse(@PathVariable Long userId, @PathVariable Long courseId, String courseName) {

        List<EntityModel<StudyCard>> cards = repository.getReviews(userId, courseId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(StudyCardController.class).studyCourse(userId, courseId, courseName)).withSelfRel());
    }

    @PostMapping("/user/{userId}/study-course/{courseId}/card/{difficulty}")
    public ResponseEntity<?> studyCard(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long difficulty) {


        return ResponseEntity.ok()
                .header("Custom-Header", "foo")
                .body("Custom header set");
    }



}
