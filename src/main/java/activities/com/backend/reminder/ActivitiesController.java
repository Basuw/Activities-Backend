package activities.com.backend.reminder;

import activities.com.backend.reminder.models.Achieve;
import activities.com.backend.reminder.models.Activity;
import activities.com.backend.reminder.models.Save;
import activities.com.backend.reminder.models.User;
import activities.com.backend.reminder.repositories.AchieveRepository;
import activities.com.backend.reminder.repositories.ActivityRepository;
import activities.com.backend.reminder.repositories.SaveRepository;
import activities.com.backend.reminder.repositories.UserRepository;
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
    private final AchieveRepository achieveRepository;
    private final SaveRepository saveRepository;

    @Autowired
    public ActivitiesController(ActivityRepository activityRepository, UserRepository userRepository, AchieveRepository achieveRepository,SaveRepository saveRepository){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.achieveRepository = achieveRepository;
        this.saveRepository = saveRepository;
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
    public ResponseEntity<User> getUserById(@PathVariable long id){
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
    // ACHIEVE
    @GetMapping("/achieve")
    public List<Achieve> getAllAchieve(@RequestParam("usrId") long usrId){
        try {
            return this.achieveRepository.findByUsrId(usrId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all achieve by user");
        }
    }
    @GetMapping("/achieve/all")
    public List<Achieve> getAllAchieveByUser(){
        try {
            return this.achieveRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all achieve by user");
        }
    }

    @GetMapping("/achieve/{id}")
    public ResponseEntity<Achieve> getAchieveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.achieveRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }
    @PostMapping("/achieve")
    public ResponseEntity<String> addAchieve(@RequestBody Achieve achieve){
        try {
            achieve.setDateAchieved(new Date());
            this.achieveRepository.save(achieve);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+achieve);
    }
    @DeleteMapping("/achieve/{id}")
    public ResponseEntity<String> deleteAchieve(@PathVariable long id){
        try {
            this.achieveRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
        return ResponseEntity.ok().body("deleted "+id);
    }
    // SAVE
    @GetMapping("/save/usrid")
    public List<Save> getAllASaveByUserId(@RequestParam("usrId") long usrId){
        try {
            return this.saveRepository.findByUsrId(usrId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all save by user");
        }
    }
    @GetMapping("/save/all")
    public List<Save> getAllSave(){
        try {
            return this.saveRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all save by user");
        }
    }

    @GetMapping("/save/{id}")
    public ResponseEntity<Save> getSaveById(@PathVariable long id){
        try {
            return ResponseEntity.ok().body(this.saveRepository.findById(id));
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting save with id : "+id);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<String> addSave(@RequestBody Save save){
        try {
            this.saveRepository.save(save);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
        return ResponseEntity.ok().body("added "+save);
    }
    @DeleteMapping("/save/{id}")
    public ResponseEntity<String> deleteSave(@PathVariable long id){
        try {
            this.saveRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding save");
        }
        return ResponseEntity.ok().body("deleted save with id : "+id);
    }
}
