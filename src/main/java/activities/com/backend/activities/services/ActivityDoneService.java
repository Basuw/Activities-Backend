package activities.com.backend.activities.services;

import activities.com.backend.activities.dto.ActivityDoneDTO;
import activities.com.backend.activities.mapper.ActivityDoneMapper;
import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.DayEnum;
import activities.com.backend.activities.models.StatusEnum;
import activities.com.backend.activities.repositories.ActivityDoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActivityDoneService {

    private final Logger LOGGER = LoggerFactory.getLogger(ActivityDoneService.class);

    private final ActivityDoneRepository activityDoneRepository;
    private final ActivitySaveService activitySaveService;

    @Autowired
    public ActivityDoneService(ActivityDoneRepository activityDoneRepository, ActivitySaveService activitySaveService){
        this.activityDoneRepository = activityDoneRepository;
        this.activitySaveService = activitySaveService;
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

    public List<ActivityDone> activityDoneByActivityIdAndUserId(long activitySaveId) {
        try {
            return  activityDoneRepository.getAllByActivitySaveId(activitySaveId);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity");
        }
    }

    public List<ActivityDone> activityDoneByActivityIdAndUserIdAndDoneOn(long activitySaveId, Date date) {
        try {
            return  activityDoneRepository.getAllByActivitySaveIdAndDoneOnIsGreaterThanEqual(activitySaveId,date);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity");
        }
    }

    public List<ActivityDone> activityDoneByActivityIdAndUserIdAndDoneOnBetween(long activitySaveId, Date begindate, Date enddate) {
        try {
            return  activityDoneRepository.getAllByActivitySaveIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activitySaveId,begindate,enddate);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activity");
        }
    }

    public Double progressByActUsrIdBeginEndDate(long activityId, Date begindate, Date enddate) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySaveIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activityId,begindate,enddate);
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

    public List<ActivityDoneDTO> progressByActSaveIdBeginEndDateDetailed(long activitySaveId, Date beginDate, Date endDate) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySaveIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activitySaveId,beginDate,endDate);
            List<ActivityDoneDTO> activityDoneDTOList = new ArrayList<>();
            for (ActivityDone activityDone : activityDoneList){
                activityDoneDTOList.add(ActivityDoneMapper.INSTANCE.toDto(activityDone));
            }
            return activityDoneDTOList;
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting progress");
        }
    }

    public ActivityDone updateAchieve(long id, StatusEnum status, Date duration) {
        try {
            ActivityDone activityDone = activityDoneRepository.findById(id);
            activityDone.setStatus(status);
            activityDone.setDuration(duration);
            return activityDoneRepository.save(activityDone);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error updating achieve");
        }
    }

    public List<ActivityDoneDTO> getDayActivitiesByUserIdAndDateAndDay(long userId, Date date) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySave_UserIdAndDoneOn(userId,date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            DayEnum day = DayEnum.values()[calendar.get(Calendar.DAY_OF_WEEK)];
            LOGGER.info("DayOFWeek : {}", Calendar.DAY_OF_WEEK);
            LOGGER.info("calendar.get(Calendar.DAY_OF_WEEK) : {}", calendar.get(Calendar.DAY_OF_WEEK));
            LOGGER.info("Getting all activities done by user with id: {}, on day{}", userId, day);
            List<ActivitySave> activitySaveList = activitySaveService.getSaveByUserIdAndDay(userId,day);
            activitySaveList.forEach(activitySave -> {
                if (activityDoneList.stream().noneMatch(activityDone -> activityDone.getActivitySave().getId() == activitySave.getId())){
                    ActivityDone activityDone = new ActivityDone();
                    activityDone.setActivitySave(activitySave);
                    activityDone.setDoneOn(date);
                    activityDone.setAchievement(0);
                    activityDone.setStatus(StatusEnum.NOT_STARTED);
                    activityDoneList.add(activityDone);
                }
            });
            return toDtos(activityDoneList);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting day activities");
        }
    }

    public List<ActivityDoneDTO> toDtos(List<ActivityDone> activityDoneList) {
        List<ActivityDoneDTO> activityDoneDTOList = new ArrayList<>();
        for (ActivityDone activityDone : activityDoneList){
            activityDoneDTOList.add(ActivityDoneMapper.INSTANCE.toDto(activityDone));
        }
        return activityDoneDTOList;
    }
}
