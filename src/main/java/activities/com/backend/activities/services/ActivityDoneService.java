package activities.com.backend.activities.services;

import activities.com.backend.activities.dto.*;
import activities.com.backend.activities.mapper.ActivityDoneMapper;
import activities.com.backend.activities.mapper.ActivityMapper;
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

    private final CalendarService calendarService;

    @Autowired
    public ActivityDoneService(ActivityDoneRepository activityDoneRepository, ActivitySaveService activitySaveService, CalendarService calendarService){
        this.activityDoneRepository = activityDoneRepository;
        this.activitySaveService = activitySaveService;
        this.calendarService = calendarService;
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

    public ActivityProgressDTO addAchieve(ActivityDone activityDone, Date date){
        try {
            LOGGER.info("date : {}", date);
            activityDone.setDoneOn(date);
            ActivityDone activityDoneSave= this.activityDoneRepository.save(activityDone);
            return activityProgressDTOFromActivityDone(activityDoneSave,activityDone.getActivitySave().getUser().getId(),date);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error adding user");
        }
    }

    public void deleteAchieve(long id){
        try {
            this.activityDoneRepository.deleteById(id);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error deleting achieve");
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

    public Double progressByActIdAndUserIDBeginEndDate(long activityId, long userId, Date begindate, Date enddate) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySaveActivityIdAndActivitySaveUserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activityId,userId,begindate,enddate);
            if (activityDoneList.isEmpty()){
                return 0.0;
            }
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

    public int numberOfActivityFinishedBetweenDates(long activityId, long userId, Date begindate, Date enddate) {
        final int[] count = {0};
        activityDoneRepository.getAllByActivitySaveActivityIdAndActivitySaveUserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activityId,userId,begindate,enddate).forEach(activityDone -> {
            if (activityDone.getAchievement()==activityDone.getActivitySave().getObjective()){
                count[0]++;
            }
        });
        return count[0];
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

    public List<ActivityDoneDTO> progressByActIdAndUserIDBeginEndDateDetailed(long activityId, long userId,Date beginDate, Date endDate) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySaveActivityIdAndActivitySaveUserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(activityId, userId,beginDate,endDate);
            List<ActivityDoneDTO> activityDoneDTOList = new ArrayList<>();
            for (ActivityDone activityDone : activityDoneList){
                activityDoneDTOList.add(ActivityDoneMapper.INSTANCE.toDto(activityDone));
            }
            return activityDoneDTOList;
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting progress");
        }
    }

    public ActivityProgressDTO updateAchieve(long id, float achievement, StatusEnum status, int mark, String notes, Date doneOn, Date duration) {
        try {
            ActivityDone activityDone = activityDoneRepository.findById(id);
            activityDone.setStatus(status);
            activityDone.setDuration(duration);
            activityDone.setAchievement(achievement);
            activityDone.setMark(mark);
            activityDone.setNotes(notes);
            activityDone.setDoneOn(doneOn);
            ActivityDone activityDoneSave = activityDoneRepository.save(activityDone);
            return activityProgressDTOFromActivityDone(activityDoneSave,activityDone.getActivitySave().getUser().getId(),doneOn);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error updating achieve");
        }
    }

    public List<ActivityDone> getActivitiesDoneByUserIdAndDateAndActivitySaveOnDay(long userId, Date date) {
        try {
            List<ActivityDone> activityDoneList = activityDoneRepository.getAllByActivitySave_UserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(userId,date,calendarService.getDateWithEndOfDay(date));
            DayEnum day = calendarService.getDayFromDate(date);
            List<ActivitySave> activitySaveList = activitySaveService.getSaveByUserIdAndDay(userId,day);
            activitySaveList.addAll(activitySaveService.getSaveByUserIdAndDay(userId,null));
            activityDoneList.addAll(saveToDone(activitySaveList,activityDoneList));
            return activityDoneList;
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting day activities ->" + exception.getMessage());
        }
    }

    public List<ActivityProgressDTO> getWeekActivitiesByUserIdAndDate(long userId, Date date) {
        try {
            List<ActivityDone> activityDoneList = this.getActivitiesDoneByUserIdAndDateAndActivitySaveOnDay(userId,date);

            return activityProgressDTOListFromActivityDoneList(activityDoneList,userId,date);
        }catch (RuntimeException exception){
            throw new RuntimeException("Error getting week activities "+ exception.getMessage());
        }
    }

    public List<ActivityProgressDTO> activityProgressDTOListFromActivityDoneList(List<ActivityDone> activityDoneList, long userId, Date date) {
        List<ActivityProgressDTO> activityProgressDTOList = new ArrayList<>();
        activityDoneList.forEach(activityDone -> activityProgressDTOList.add(activityProgressDTOFromActivityDone(activityDone,userId,date)));
        return activityProgressDTOList;
    }

    public ActivityProgressDTO activityProgressDTOFromActivityDone(ActivityDone activityDone, long userId, Date date) {
        //TO DTOs
        ActivityDTO activityDTO = ActivityMapper.INSTANCE.toDto(activityDone.getActivitySave().getActivity());
        ActivitySaveWtActivityDTO activitySaveWtActivityDTO = new ActivitySaveWtActivityDTO(activityDone.getActivitySave(),activityDTO);
        ActivityDoneWtActivitySaveDTO activityDoneWtActivitySaveDTO = new ActivityDoneWtActivitySaveDTO(activityDone,activitySaveWtActivityDTO);

        return new ActivityProgressDTO(
                activityDoneWtActivitySaveDTO,
                progressByActIdAndUserIDBeginEndDate(activityDone.getActivitySave().getActivity().getId(), userId,calendarService.getDateOfFirstDayOfWeek(date), calendarService.getDateOfLastDayOfWeek(date)),
                numberOfActivityFinishedBetweenDates(activityDone.getActivitySave().getActivity().getId(), userId,calendarService.getDateOfFirstDayOfWeek(date), calendarService.getDateOfLastDayOfWeek(date))
        );
    }

    public List<ActivityDone> saveToDone(List<ActivitySave> activitySaveList, List<ActivityDone> activityDoneList) {
        List<ActivityDone> activityDoneListFromSave= new ArrayList<>();
        activitySaveList.forEach(activitySave -> {
            if (activityDoneList.stream().noneMatch(activityDone -> activityDone.getActivitySave().getId() == activitySave.getId())){
                ActivityDone activityDone = new ActivityDone();
                activityDone.setActivitySave(activitySave);
                activityDone.setDoneOn(null);
                activityDone.setAchievement(0);
                activityDone.setStatus(StatusEnum.NOT_STARTED);
                activityDoneListFromSave.add(activityDone);
            }
        });
        return activityDoneListFromSave;
    }

    public List<ActivityDoneDTO> toDtos(List<ActivityDone> activityDoneList) {
        List<ActivityDoneDTO> activityDoneDTOList = new ArrayList<>();
        for (ActivityDone activityDone : activityDoneList){
            activityDoneDTOList.add(ActivityDoneMapper.INSTANCE.toDto(activityDone));
        }
        return activityDoneDTOList;
    }
}
