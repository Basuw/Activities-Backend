package activities.com.backend.reminder.services;

import activities.com.backend.reminder.models.Activity;
import activities.com.backend.reminder.repositories.ActivitiesRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    private final ActivitiesRepository repository;
    public ActivityService(ActivitiesRepository repository){
        this.repository=repository;
    }

    public Activity addActivity(Activity activity){
        try {
            repository.save(activity);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error while adding new activity");
        }
        return activity;
    }
}
