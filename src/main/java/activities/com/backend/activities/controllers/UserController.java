package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.User;
import activities.com.backend.activities.services.ActivityDoneService;
import activities.com.backend.activities.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static activities.com.backend.activities.utilities.*;

@RestController
@RequestMapping(ACTIVITY)
public class UserController {

    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityDoneService.class);

    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            return ResponseEntity.ok().body(userService.getAllUsers());
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all users");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity< User> getUserById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(userService.getUserById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        try {
            LOGGER.info("Saving user: {}", user);
            return ResponseEntity.ok().body(userService.addUser(user));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        try {
            LOGGER.info("Deleting user: with id: {}", id);
            userService.deleteUser(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }
}
