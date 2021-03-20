package com.learndeck.domain.card;

import com.learndeck.web.CardController;
import com.learndeck.web.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardModelAssembler implements RepresentationModelAssembler<Card, EntityModel<Card>> {

    @Override
    public EntityModel<Card> toModel(Card user) {
        return EntityModel.of(user,
                linkTo(methodOn(CardController.class).one(user.getCardId())).withSelfRel(),
                linkTo(methodOn(CardController.class).all()).withRel("cards"));
    }
}
