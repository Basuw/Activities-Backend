package activities.com.backend.activities;

import activities.com.backend.activities.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesService {
    private final ActivityRepository repository;
    public ActivitiesService(ActivityRepository repository){
        this.repository=repository;
    }


}
