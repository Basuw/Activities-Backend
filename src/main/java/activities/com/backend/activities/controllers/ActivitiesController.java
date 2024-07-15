package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static activities.com.backend.activities.utilities.*;

import java.util.List;

@RestController
@RequestMapping(ACTIVITY)
public class ActivitiesController {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivitiesController(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @GetMapping("/activity")
    public List<Activity> getAllActivities(){
        try {
            return this.activityRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<Activity> getActivitiesById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.activityRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }
    @PostMapping("/activity")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity){
        try {
            this.activityRepository.save(activity);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
        return ResponseEntity.ok().body(activity);
    }
    @DeleteMapping("/activity/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable long id){
        try {
            this.activityRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }
}
