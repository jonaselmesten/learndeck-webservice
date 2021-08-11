package com.learndeck.domain.user;

import com.learndeck.web.UserCourseController;
import com.learndeck.web.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(user.getUserId())).withSelfRel(),
                linkTo(methodOn(UserCourseController.class).studentCourses(user.getUserId())).withRel("user_courses"));
    }
}
