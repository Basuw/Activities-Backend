package activities.com.backend.activities;

import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.User;
import activities.com.backend.activities.repositories.ActivityDoneRepository;
import activities.com.backend.activities.repositories.ActivityRepository;
import activities.com.backend.activities.repositories.ActivitySaveRepository;
import activities.com.backend.activities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/activities/reminder")
public class ActivitiesController {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ActivityDoneRepository activityDoneRepository;
    private final ActivitySaveRepository activitySaveRepository;

    @Autowired
    public ActivitiesController(ActivityRepository activityRepository, UserRepository userRepository, ActivityDoneRepository activityDoneRepository, ActivitySaveRepository activitySaveRepository){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.activityDoneRepository = activityDoneRepository;
        this.activitySaveRepository = activitySaveRepository;
    }

    // ACTIVITY

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
    @PostMapping("/activity/")
    public ResponseEntity<String> addActivity(@RequestBody Activity activity){
        try {
            this.activityRepository.save(activity);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
        return ResponseEntity.ok().body("added "+activity);
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
    // USER
    @GetMapping("/user")
    public List<User> getAllUsers(){
        try {
            return this.userRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all users");
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity< User> getUserById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.userRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }
    @PostMapping("/user")
    public ResponseEntity<String> adUser(@RequestBody User user){
        try {
            this.userRepository.save(user);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+user);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        try {
            this.userRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }


    //------- ACHIEVE ---------\\
/*    @GetMapping("/achieve/{user_id}")
    public List<ActivityDone> getAllAchieve(@RequestParam("userId") long usrId){
        try {
            return this.activityDoneRepository.findByUserId(usrId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all achieve by user");
        }
    }*/
    @GetMapping("/achieve/all")
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


    //-------- SAVE ---------\\
/*    @GetMapping("/save/{user_id}")
    public List<ActivitySave> getAllASaveByUserId(@RequestParam("usrId") long usrId){
        try {
            return this.activitySaveRepository.findByUsrId(usrId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all save by user");
        }
    }*/
    @GetMapping("/save/all")
    public List<ActivitySave> getAllSave(){
        try {
            return this.activitySaveRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all save by user");
        }
    }

    @GetMapping("/save/{id}")
    public ResponseEntity<ActivitySave> getSaveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.activitySaveRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting save with id : "+id);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<String> addSave(@RequestBody ActivitySave activitySave){
        try {
            this.activitySaveRepository.save(activitySave);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+ activitySave);
    }
    @DeleteMapping("/save/{id}")
    public ResponseEntity<String> deleteSave(@PathVariable long id){
        try {
            this.activitySaveRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding save");
        }
        return ResponseEntity.ok().body("deleted save with id : "+id);
    }
}
