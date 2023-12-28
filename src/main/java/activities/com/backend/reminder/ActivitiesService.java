package activities.com.backend.reminder;

import activities.com.backend.reminder.models.Activity;
import activities.com.backend.reminder.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesService {
    private final ActivityRepository repository;
    public ActivitiesService(ActivityRepository repository){
        this.repository=repository;
    }


}
