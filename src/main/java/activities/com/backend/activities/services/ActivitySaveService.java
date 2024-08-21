package activities.com.backend.activities.services;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.DayEnum;
import activities.com.backend.activities.repositories.ActivitySaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitySaveService {

    private final ActivitySaveRepository activitySaveRepository;

    public ActivitySaveService(ActivitySaveRepository activitySaveRepository) {
        this.activitySaveRepository = activitySaveRepository;
    }

    public List<ActivitySave> getAllSave(){
        try {
            return activitySaveRepository.findAll();
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting all save by user");
        }
    }

    public ActivitySave getSaveById(long id){
        try {
            return activitySaveRepository.findById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting save with id : "+id);
        }
    }

    public void addSave(ActivitySave activitySave){
        try {
            this.activitySaveRepository.save(activitySave);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
    }

    public void deleteSave(long id){
        try {
            activitySaveRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding save");
        }
    }

    public List<ActivitySave> getUserSaves(int userId){
        try {
            return activitySaveRepository.findAllByUserId(userId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting user saves");
        }
    }

    public List<ActivitySave> getSaveByUserIdAndDay(long userId, DayEnum day){
        try {
            return activitySaveRepository.findAllByUserIdAndDay(userId, day);
        } catch (RuntimeException exception) {
            throw new RuntimeException("Error getting save by user with id : " + userId);
        }
    }
}
