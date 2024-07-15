package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.User;
import activities.com.backend.activities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static activities.com.backend.activities.utilities.*;

@RestController
@RequestMapping(ACTIVITY)
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public List<User> getAllUsers(){
        try {
            return this.userRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all users");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity< User> getUserById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.userRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            this.userRepository.save(user);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+user);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        try {
            this.userRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }


    ///-----------------//

}
