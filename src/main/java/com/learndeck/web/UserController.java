package com.learndeck.web;

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
public class UserController {

    @Autowired
    private UserRepository repository;

    private final UserModelAssembler assembler;

    public UserController(UserModelAssembler assembler) {
        this.assembler = assembler;
    }

    /**
     * Get all the users.
     */
    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all(){

        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    /**
     * Get a specific user.
     */
    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }

}
