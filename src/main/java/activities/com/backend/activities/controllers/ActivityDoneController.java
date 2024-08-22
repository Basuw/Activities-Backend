package activities.com.backend.activities.controllers;

import activities.com.backend.activities.dto.ActivityDoneDTO;
import activities.com.backend.activities.dto.ActivityProgressDTO;
import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.StatusEnum;
import activities.com.backend.activities.services.ActivityDoneService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static activities.com.backend.activities.utilities.ACTIVITY;

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
            LOGGER.info("Getting all activities done by user with id: {}", user_id);
            return ResponseEntity.ok().body(activityDoneService.getAchieveByUserId(user_id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+user_id);
        }
    }

    @GetMapping("/day_activities/user_id/{user_id}")
    @ApiOperation("Get all activities done and save by user for a day")
    public ResponseEntity<List<ActivityProgressDTO>> getDayActivitiesByUserId(@PathVariable long user_id, @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date date){
        try {
            LOGGER.info("date : {}", date);
            return ResponseEntity.ok().body(activityDoneService.getWeekActivitiesByUserIdAndDate(user_id,date));
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

    @PatchMapping("/achieve/{id}")
    @ApiOperation("update status and duration of activity done")
    public ResponseEntity<ActivityDone> updateAchieve(@PathVariable long id,
                                                      @RequestParam StatusEnum status,
                                                      @RequestParam @DateTimeFormat(pattern= "HH:mm:ss") Date duration){
        try {
            return ResponseEntity.ok().body(activityDoneService.updateAchieve(id,status,duration));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
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

    @GetMapping("/achieve/activity_id")
    @ApiOperation("Activity done by user id and activity id")
    public ResponseEntity<List<ActivityDone>> getActivityDoneByActivityAndUserId(@RequestParam long activity_save_id){
        try {
            return ResponseEntity.ok().body(activityDoneService.activityDoneByActivityIdAndUserId(activity_save_id));
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping("/achieve/after_date")
    @ApiOperation("Activity done by user id and activity id date later than given date")
    public ResponseEntity<List<ActivityDone>> getActivityDoneByActivityIdAndUserIdAndDoneOnAfter(@RequestParam long activity_save_id, @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date date){
        try {
            return ResponseEntity.ok().body(activityDoneService.activityDoneByActivityIdAndUserIdAndDoneOn(activity_save_id,date));
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping("/achieve/between_date")
    @ApiOperation("Activity done by user id and activity id date between given dates")
    public ResponseEntity<List<ActivityDone>> getActivityDoneByActivityIdAndUserIdAndDoneOnBetween(@RequestParam long activity_save_id,
                                                                                                   @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date begin_date,
                                                                                                   @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date end_date){
        try {
            return ResponseEntity.ok().body(activityDoneService.activityDoneByActivityIdAndUserIdAndDoneOnBetween(activity_save_id,begin_date,end_date));
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping("/achieve/progress/activity")
    @ApiOperation("Activity done by user id and activity id date later than given date")
    public ResponseEntity<Double> getProgressByActUsrIdBeginEndDate(@RequestParam long activity_save_id,
                                                                    @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date begin_date,
                                                                    @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date end_date){
        try {
            return ResponseEntity.ok().body(activityDoneService.progressByActUsrIdBeginEndDate(activity_save_id,begin_date,end_date));
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping("/achieve/progress/detailed")
    @ApiOperation("Activity done by user id and activity id date later than given date")
    public ResponseEntity<List<ActivityDoneDTO>> getProgressByActSaveIdBeginEndDateDetailed(@RequestParam long activity_save_id,
                                                                    @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date begin_date,
                                                                    @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date end_date){
        try {
            return ResponseEntity.ok().body(activityDoneService.progressByActSaveIdBeginEndDateDetailed(activity_save_id,begin_date,end_date));
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping("/achieve/progress/user")
    @ApiOperation("Activity done by user id and activity id date later than given date")
    public ResponseEntity<Map<ActivitySave,Double>> getProgressByUsrIdBeginEndDate(@RequestParam int user_id,
                                                                                   @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date begin_date,
                                                                                   @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date end_date){
        try {
            return ResponseEntity.ok().body(activityDoneService.progressByUsrIdBeginEndDate(user_id,begin_date,end_date));
        }catch (RuntimeException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
