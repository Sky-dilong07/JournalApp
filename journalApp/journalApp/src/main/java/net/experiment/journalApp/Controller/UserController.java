package net.experiment.journalApp.Controller;

import net.experiment.journalApp.entity.User;
import net.experiment.journalApp.repository.UserRepository;
import net.experiment.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);

        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());

        userService.saveUser(userInDb);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        userRepository.deleteByUsername(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
