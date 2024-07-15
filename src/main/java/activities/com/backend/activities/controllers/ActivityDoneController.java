package activities.com.backend.activities.controllers;

import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.repositories.ActivityDoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static activities.com.backend.activities.utilities.*;

@RestController
@RequestMapping(ACTIVITY)
public class ActivityDoneController {

    private final ActivityDoneRepository activityDoneRepository;

    @Autowired
    public ActivityDoneController(ActivityDoneRepository activityDoneRepository){
        this.activityDoneRepository = activityDoneRepository;
    }

    @GetMapping("/achieve")
    public List<ActivityDone> getAllAchieveByUser(){
        try {
            return this.activityDoneRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all achieve by user");
        }
    }

    @GetMapping("/achieve/{id}")
    public ResponseEntity<ActivityDone> getAchieveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.activityDoneRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }
    @PostMapping("/achieve")
    public ResponseEntity<String> addAchieve(@RequestBody ActivityDone activityDone){
        try {
            activityDone.setDoneOn(new Date());
            this.activityDoneRepository.save(activityDone);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+ activityDone);
    }
    @DeleteMapping("/achieve/{id}")
    public ResponseEntity<String> deleteAchieve(@PathVariable long id){
        try {
            this.activityDoneRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }
}
