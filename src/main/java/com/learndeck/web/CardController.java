package com.learndeck.web;

import com.learndeck.domain.card.Card;
import com.learndeck.domain.card.CardModelAssembler;
import com.learndeck.domain.card.CardNotFoundException;
import com.learndeck.domain.card.CardRepository;
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
public class CardController {

    @Autowired
    private CardRepository repository;

    private final CardModelAssembler assembler;

    public CardController(CardModelAssembler assembler) {
        this.assembler = assembler;
    }

    /**
     * Get all the cards from all the courses.
     */
    @GetMapping("/courses/cards")
    public CollectionModel<EntityModel<Card>> all(){

        List<EntityModel<Card>> cards = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(CardController.class).all()).withSelfRel());
    }

    /**
     * Get a single card.
     */
    @GetMapping("/courses/cards/{id}")
    public EntityModel<Card> one(@PathVariable(name = "id") Long cardId) {

        Card card = repository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException(cardId));

        return assembler.toModel(card);
    }

    /**
     * Get all the cards from a course.
     */
    @GetMapping("/courses/{id}/cards")
    public CollectionModel<EntityModel<Card>> allCards(@PathVariable(name = "id") Long courseId){

        List<EntityModel<Card>> cards = repository.getCardsFromCourse(courseId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(CardController.class).allCards(courseId)).withSelfRel());
    }

}
