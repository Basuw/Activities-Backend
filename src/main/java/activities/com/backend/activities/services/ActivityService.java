package activities.com.backend.activities.services;

import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities(){
        try {
            return this.activityRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    public Activity getActivitiesById(long id){
        try {
            return activityRepository.findById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all activities");
        }
    }

    public Activity addActivity(Activity activity){
        try {
            return activityRepository.save(activity);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
    }

    public void deleteActivity(long id){
        try {
            this.activityRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding activity");
        }
    }

    public List<Activity> commonActivities() {
        return activityRepository.findAllByUser(null);
    }
}
