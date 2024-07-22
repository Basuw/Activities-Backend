package activities.com.backend.activities.services;

import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.repositories.ActivityDoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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

    public List<ActivityDone> activityDoneByActivityIdAndUserId(long activityId, long userId) {
        try {
            return  activityDoneRepository.getAllByActivitySave_ActivityIdAndActivitySave_UserId(activityId, userId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity");
        }
    }

    public List<ActivityDone> activityDoneByActivityIdAndUserIdAndDoneOn(long activityId, long userId, Date date) {
        try {
            return  activityDoneRepository.getAllByActivitySave_ActivityIdAndActivitySave_UserIdAndDoneOnIsGreaterThanEqual(activityId, userId,date);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity");
        }
    }

    public List<ActivityDone> activityDoneByActivityIdAndUserIdAndDoneOnBetween(long activityId, long userId, Date begindate, Date enddate) {
        try {
            return  activityDoneRepository.getAllByActivitySave_ActivityIdAndActivitySave_UserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activityId, userId,begindate,enddate);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity");
        }
    }

    public Double progressByActUsrIdBeginEndDate(long activityId, long userId, Date begindate, Date enddate) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySave_ActivityIdAndActivitySave_UserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activityId, userId,begindate,enddate);
            float progress = 0;
            float objective = activityDoneList.getFirst().getActivitySave().getObjective();
            float frequency = activityDoneList.getFirst().getActivitySave().getFrequency();
            for (ActivityDone activityDone : activityDoneList){
                progress += activityDone.getAchievement();
            }

            return (double) ((progress/(objective*frequency))*100);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting progress");
        }
    }

    public Map<ActivitySave,Double> progressByUsrIdBeginEndDate(long userId, Date begindate, Date enddate) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySave_UserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(userId,begindate,enddate);
            Map<ActivitySave,Double> progressMap = new HashMap<>();
            for (ActivityDone activityDone : activityDoneList){
                float progress = progressMap.containsKey(activityDone.getActivitySave()) ? progressMap.get(activityDone.getActivitySave()).floatValue() : 0;
                float objective = activityDone.getActivitySave().getObjective();
                float frequency = activityDone.getActivitySave().getFrequency();
                progress += activityDone.getAchievement();
                progressMap.put(activityDone.getActivitySave(),(double) ((progress/(objective*frequency))*100));
            }
            return progressMap;
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting progress for all activities");
        }
    }
}
