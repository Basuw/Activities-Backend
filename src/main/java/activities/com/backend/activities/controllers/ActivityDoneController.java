package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.services.ActivityDoneService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static activities.com.backend.activities.utilities.*;

@RestController
@RequestMapping(ACTIVITY)
public class ActivityDoneController {

    private final ActivityDoneService activityDoneService;
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityDoneService.class);


    @Autowired
    public ActivityDoneController(ActivityDoneService activityDoneService){
        this.activityDoneService = activityDoneService;
    }

    @GetMapping("/achieve")
    @ApiOperation("Get all activities done")
    public ResponseEntity<List<ActivityDone>> getAllAchieve(){
        try {
            return ResponseEntity.ok().body(activityDoneService.getAllAchieve());
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all achieve by user");
        }
    }

    @GetMapping("/achieve/{id}")
    @ApiOperation("Get activity done by id")
    public ResponseEntity<ActivityDone> getAchieveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(activityDoneService.getAchieveById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }

    @GetMapping("/achieve/user_id/{user_id}")
    @ApiOperation("Get all activities done by user")
    public ResponseEntity<List<ActivityDone>> getAchieveByUserId(@PathVariable long user_id){
        try {
            return ResponseEntity.ok().body(activityDoneService.getAchieveByUserId(user_id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+user_id);
        }
    }

    @PostMapping("/achieve")
    @ApiOperation("Add activity done")
    public ResponseEntity<ActivityDone> addAchieve(@RequestBody ActivityDone activityDone){
        try {
            LOGGER.info("Saving activity done: {}", activityDone);
            return  ResponseEntity.ok().body(activityDoneService.addAchieve(activityDone));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity done");
        }
    }

    @DeleteMapping("/achieve/{id}")
    @ApiOperation("Delete activity done")
    public ResponseEntity<String> deleteAchieve(@PathVariable long id){
        try {
            LOGGER.info("Deleting activity done: with id: {}", id);
            activityDoneService.deleteAchieve(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }

/*    @GetMapping("/achieve/week_progress/{activity_id}")
    @ApiOperation("Progress of activity done in a week")
    public ResponseEntity<Map<Activity,Float>> getWeekActivityProgress(@PathVariable long activity_id, @RequestParam Date beginning){
        try {
            return ResponseEntity.ok().body(activityDoneService.getWeekActivityProgress(activity_id, beginning));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
    }*/

    @GetMapping("/achieve/activity_id/{activity_id}")
    @ApiOperation("Progress of activity done in a week")
    public ResponseEntity<List<ActivityDone>> getWeekActivity(@PathVariable long activity_id/*, @RequestParam Date beginning_date*/){
        try {
            return ResponseEntity.ok().body(activityDoneService.getWeekActivity(activity_id/*, beginning_date*/));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
    }
}
