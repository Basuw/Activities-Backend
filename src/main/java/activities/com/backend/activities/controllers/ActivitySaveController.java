package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.services.ActivityDoneService;
import activities.com.backend.activities.services.ActivitySaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static activities.com.backend.activities.utilities.*;

@RestController
@RequestMapping(ACTIVITY)
public class ActivitySaveController {

    private final ActivitySaveService activitySaveService;
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityDoneService.class);

    @Autowired
    public ActivitySaveController(ActivitySaveService activitySaveService) {
        this.activitySaveService = activitySaveService;
    }


    @GetMapping("/save")
    public List<ActivitySave> getAllSave(){
        try {
            return activitySaveService.getAllSave();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all save by user");
        }
    }

    @GetMapping("/save/{id}")
    public ResponseEntity<ActivitySave> getSaveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(activitySaveService.getSaveById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting save with id : "+id);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<String> addSave(@RequestBody ActivitySave activitySave){
        try {
            activitySaveService.addSave(activitySave);
            LOGGER.info("Saving activity save: {}", activitySave);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+ activitySave);
    }
    @DeleteMapping("/save/{id}")
    public ResponseEntity<String> deleteSave(@PathVariable long id){
        try {

            activitySaveService.deleteSave(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding save");
        }
        return ResponseEntity.ok().body("deleted save with id : "+id);
    }


    @GetMapping("/save/user_id/{userId}")
    @ResponseBody
    public ResponseEntity<List<ActivitySave>> getUserSaves(@PathVariable int userId){
        try {
            return ResponseEntity.ok().body(activitySaveService.getUserSaves(userId));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user saves");
        }
    }
}
