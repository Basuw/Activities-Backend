package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.services.ActivityDoneService;
import activities.com.backend.activities.services.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static activities.com.backend.activities.utilities.*;

import java.util.List;

@RestController
@RequestMapping(ACTIVITY)
public class ActivitiesController {

    private final ActivityService activityService;
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityDoneService.class);

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

    @GetMapping("/activity/common")
    public ResponseEntity<List<Activity>> getCommonActivities(){
        try {
            return ResponseEntity.ok().body(activityService.commonActivities());
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }


    @PostMapping("/activity")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity){
        try {
            LOGGER.info("Saving activity: {}", activity);
            return ResponseEntity.ok().body(activityService.addActivity(activity));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
    }
    @DeleteMapping("/activity/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable long id){
        try {
            LOGGER.info("Deleting activity: with id: {}", id);
            activityService.deleteActivity(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }
}
