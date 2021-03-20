package user;

import user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import user.UserNotFoundException;
import user.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    public List<User> index(){
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    public User one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


}
