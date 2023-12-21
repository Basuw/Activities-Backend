package activities.com.backend.reminder.controllers;

import activities.com.backend.reminder.models.Activity;
import activities.com.backend.reminder.repositories.ActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities/reminder")
public class ActivitiesController {

    private final ActivitiesRepository activitiesRepository;

    @Autowired
    public ActivitiesController(ActivitiesRepository activitiesRepository){
        this.activitiesRepository=activitiesRepository;
    }

    @GetMapping("/all")
    public List<Activity> getAllActivities(){
        try {
            return this.activitiesRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }
    @GetMapping("/test")
    public String test(){
        return "C'est cool";
    }
    @PostMapping
    public @ResponseBody
}
