package activities.com.backend.activities.controllers;

import activities.com.backend.activities.dto.ActivityDTO;
import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.services.ActivityDoneService;
import activities.com.backend.activities.services.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static activities.com.backend.activities.utilities.*;

import java.util.List;

@RestController
@RequestMapping(ACTIVITY)
public class ActivityController {

    private final ActivityService activityService;
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityDoneService.class);

    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping("/activity")
    public ResponseEntity<List<ActivityDTO>> getAllActivities(){
        try {
            return ResponseEntity.ok().body(activityService.getAllActivities());
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    @GetMapping("/activity/{id}")
    @ApiOperation("Get activity with given id")
    public ResponseEntity<Activity> getActivitiesById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(activityService.getActivitiesById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    @GetMapping("/activity/common")
    @ApiOperation("Get all common activities")
    public ResponseEntity<List<ActivityDTO>> getCommonActivities(){
        try {
            return ResponseEntity.ok().body(activityService.commonActivities());
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    @GetMapping("/activity/custom/user/{id}")
    @ApiOperation("Get all common activities plus activities created by the user given")
    public ResponseEntity<List<ActivityDTO>> getCustomUserActivities(@PathVariable long id){
        try {
            LOGGER.info("Getting activities of user with id: {}", id);
            return ResponseEntity.ok().body(activityService.userActivities(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    @GetMapping("/activity/all/user/{id}")
    @ApiOperation("Get all common activities + the one created by the user")
    public ResponseEntity<List<ActivityDTO>> getAllUserActivitiesAndCommon(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(activityService.userActivitiesAndCommon(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    @PostMapping("/activity")
    @ApiOperation("Creation of an Activity")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity){
        try {
            LOGGER.info("Saving activity: {}", activity);
            return ResponseEntity.ok().body(activityService.addActivity(activity));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
    }
    @DeleteMapping("/activity/{id}")
    @ApiOperation("Delete activity")
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
