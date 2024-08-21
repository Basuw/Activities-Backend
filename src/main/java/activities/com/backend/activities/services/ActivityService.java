package activities.com.backend.activities.services;

import activities.com.backend.activities.dto.ActivityDTO;
import activities.com.backend.activities.mapper.ActivityMapper;
import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.repositories.ActivityRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);


    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public List<ActivityDTO> getAllActivities(){
        try {
            return this.toDtos(this.activityRepository.findAll());
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

    public List<ActivityDTO> commonActivities() {
        return this.toDtos(activityRepository.findAllByUser(null));
    }

    public List<ActivityDTO> userActivities(long userId) {
        return this.toDtos(activityRepository.findAllByUserId(userId));
    }

    public List<ActivityDTO> userActivitiesAndCommon(long id) {
        List<ActivityDTO> activities = new ArrayList<>();
        activities.addAll(this.commonActivities());
        activities.addAll(this.userActivities(id));
        return activities;
    }



    public List<ActivityDTO> toDtos(List<Activity> activities) {
        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityDTO activityDTO = ActivityMapper.INSTANCE.toDto(activity);
            LOGGER.debug("Activity: {}", activityDTO);
            activityDTOS.add(activityDTO);
        }
        LOGGER.info("User activities: {}", activityDTOS);
        return activityDTOS;
    }
}
