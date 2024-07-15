package activities.com.backend.activities.services;

import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.repositories.ActivityDoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ActivityDoneService {

    private final ActivityDoneRepository activityDoneRepository;

    @Autowired
    public ActivityDoneService(ActivityDoneRepository activityDoneRepository){
        this.activityDoneRepository = activityDoneRepository;
    }


    public List<ActivityDone> getAllAchieve(){
        try {
            return this.activityDoneRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all achieve by user");
        }
    }

    public ActivityDone getAchieveById(long id){
        try {
            return activityDoneRepository.findById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user with id : "+id);
        }
    }

    public ActivityDone addAchieve(ActivityDone activityDone){
        try {
            activityDone.setDoneOn(new Date());
            return this.activityDoneRepository.save(activityDone);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
    }

    public void deleteAchieve(long id){
        try {
            this.activityDoneRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding achieve");
        }
    }

    public List<ActivityDone> getAchieveByUserId(long userId) {
        try {
            return this.activityDoneRepository.getAllByActivitySave_UserId(userId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting achieved by userId");
        }
    }

/*    public Map<Activity, Float> getWeekActivityProgress(long activityId, Date beginning) {
        try {
            return  activityDoneRepository.getAllByDoneOnIsAfterAndDoneOnBefore(activityId, beginning);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity progress");
        }
    }*/

    public List<ActivityDone> getWeekActivity(long activityId/*, Date beginning*/) {
        try {
            return  activityDoneRepository.getAllByActivitySave_ActivityId(activityId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity progress");
        }
    }
}
