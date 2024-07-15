package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.services.ActivityDoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static activities.com.backend.activities.utilities.*;

@RestController
@RequestMapping(ACTIVITY)
public class ActivityDoneController {


    private final ActivityDoneService activityDoneService;

    @Autowired
    public ActivityDoneController(ActivityDoneService activityDoneService){
        this.activityDoneService = activityDoneService;
    }

    @GetMapping("/achieve")
    public ResponseEntity<List<ActivityDone>> getAllAchieveByUser(){
        try {
            return ResponseEntity.ok().body(activityDoneService.getAllAchieveByUser());
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all achieve by user");
        }
    }

    @GetMapping("/achieve/{id}")
    public ResponseEntity<ActivityDone> getAchieveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(activityDoneService.getAchieveById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }

    @PostMapping("/achieve")
    public ResponseEntity<ActivityDone> addAchieve(@RequestBody ActivityDone activityDone){
        try {
            return  ResponseEntity.ok().body(activityDoneService.addAchieve(activityDone));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
    }

    @DeleteMapping("/achieve/{id}")
    public ResponseEntity<String> deleteAchieve(@PathVariable long id){
        try {
            activityDoneService.deleteAchieve(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }
}
