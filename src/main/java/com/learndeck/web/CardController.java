package com.learndeck.web;

import com.learndeck.domain.card.Card;
import com.learndeck.domain.card.CardModelAssembler;
import com.learndeck.domain.card.CardNotFoundException;
import com.learndeck.domain.card.CardRepository;
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
public class CardController {

    @Autowired
    private CardRepository repository;

    private final CardModelAssembler assembler;

    public CardController(CardModelAssembler assembler) {
        this.assembler = assembler;
    }

    @GetMapping("/cards")
    public CollectionModel<EntityModel<Card>> all(){

        List<EntityModel<Card>> cards = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(CardController.class).all()).withSelfRel());
    }

    @GetMapping("/cards/{id}")
    public CollectionModel<EntityModel<Card>> allCourse(@PathVariable Long id){

        List<EntityModel<Card>> cards = repository.getCardsFromClass(id).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cards,
                linkTo(methodOn(CardController.class).allCourse(id)).withSelfRel());
    }

    @GetMapping("/card/{id}")
    public EntityModel<Card> one(@PathVariable Long id) {

        Card card = repository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));

        return assembler.toModel(card);
    }


}
