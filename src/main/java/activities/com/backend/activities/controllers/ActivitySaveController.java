package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.repositories.ActivitySaveRepository;
import activities.com.backend.activities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static activities.com.backend.activities.utilities.*;

@RestController
@RequestMapping(ACTIVITY)
public class ActivitySaveController {

    private final ActivitySaveRepository activitySaveRepository;
    private final UserRepository userRepository;

    @Autowired
    public ActivitySaveController(ActivitySaveRepository activitySaveRepository, UserRepository userRepository) {
        this.activitySaveRepository = activitySaveRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/save")
    public List<ActivitySave> getAllSave(){
        try {
            return this.activitySaveRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all save by user");
        }
    }

    @GetMapping("/save/{id}")
    public ResponseEntity<ActivitySave> getSaveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.activitySaveRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting save with id : "+id);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<String> addSave(@RequestBody ActivitySave activitySave){
        try {
            this.activitySaveRepository.save(activitySave);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+ activitySave);
    }
    @DeleteMapping("/save/{id}")
    public ResponseEntity<String> deleteSave(@PathVariable long id){
        try {
            this.activitySaveRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding save");
        }
        return ResponseEntity.ok().body("deleted save with id : "+id);
    }


/*    @GetMapping("/save/user_id/{userId}")
    @ResponseBody
    public ResponseEntity<List<ActivitySave>> getUserSaves(@PathVariable int userId){
        try {
            return ResponseEntity.ok().body(this.userRepository.SaveByUserId(userId));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user saves");
        }
    }*/
}
