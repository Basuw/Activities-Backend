package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static activities.com.backend.activities.utilities.*;

import java.util.List;

@RestController
@RequestMapping(ACTIVITY)
public class ActivitiesController {

    private final ActivityService activityService;

    @Autowired
    public ActivitiesController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping("/activity")
    public ResponseEntity<List<Activity>> getAllActivities(){
        try {
            return ResponseEntity.ok().body(activityService.getAllActivities());
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<Activity> getActivitiesById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(activityService.getActivitiesById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }
    @PostMapping("/activity")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity){
        try {
            return ResponseEntity.ok().body(activityService.addActivity(activity));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
    }
    @DeleteMapping("/activity/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable long id){
        try {
            activityService.deleteActivity(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }
}
