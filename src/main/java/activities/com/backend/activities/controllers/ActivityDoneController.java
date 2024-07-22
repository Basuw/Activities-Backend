package activities.com.backend.activities.controllers;

import activities.com.backend.activities.dto.ActivityDoneDTO;
import activities.com.backend.activities.mapper.ActivityDoneMapper;
import activities.com.backend.activities.models.ActivityDone;
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

    @GetMapping("/achieve/activity_id")
    @ApiOperation("Activity done by user id and activity id")
    public ResponseEntity<List<ActivityDone>> getActivityDoneByActivityAndUserId(@RequestParam long activity_id, @RequestParam int user_id){
        try {
            return ResponseEntity.ok().body(activityDoneService.activityDoneByActivityIdAndUserId(activity_id,user_id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
    }

    @GetMapping("/achieve/after_date")
    @ApiOperation("Activity done by user id and activity id date later than given date")
    public ResponseEntity<List<ActivityDone>> getActivityDoneByActivityIdAndUserIdAndDoneOnAfter(@RequestParam long activity_id, @RequestParam int user_id, @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date date){
        try {
            return ResponseEntity.ok().body(activityDoneService.activityDoneByActivityIdAndUserIdAndDoneOn(activity_id,user_id,date));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
    }

    @GetMapping("/achieve/between_date")
    @ApiOperation("Activity done by user id and activity id date between given dates")
    public ResponseEntity<List<ActivityDone>> getActivityDoneByActivityIdAndUserIdAndDoneOnBetween(@RequestParam long activity_id,
                                                                                                   @RequestParam int user_id,
                                                                                                   @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date begin_date,
                                                                                                   @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date end_date){
        try {
            return ResponseEntity.ok().body(activityDoneService.activityDoneByActivityIdAndUserIdAndDoneOnBetween(activity_id,user_id,begin_date,end_date));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
    }

    @GetMapping("/achieve/progress")
    @ApiOperation("Activity done by user id and activity id date later than given date")
    public ResponseEntity<Double> getProgressByActUsrIdBeginEndDate(@RequestParam long activity_id,
                                                                    @RequestParam int user_id,
                                                                    @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date begin_date,
                                                                    @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd") Date end_date){
        try {
            return ResponseEntity.ok().body(activityDoneService.progressByActUsrIdBeginEndDate(activity_id,user_id,begin_date,end_date));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
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
    @GetMapping("/dto")
    @ApiOperation("DTO test")
    public ResponseEntity<ActivityDoneDTO> testDTO(){
        try {
            ActivityDone act = activityDoneService.getAchieveById(2);
            ActivityDoneDTO actDTO = ActivityDoneMapper.INSTANCE.toDto(act);
            return ResponseEntity.ok().body(actDTO);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
    }
}
