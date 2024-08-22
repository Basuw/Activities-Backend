package activities.com.backend.activities.dto;

import activities.com.backend.activities.models.ActivityDone;
import activities.com.backend.activities.models.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ActivityDoneWtActivitySaveDTO {
    private int id;
    private float achievement;
    private Date doneOn;
    private ActivitySaveWtActivityDTO activitySave;
    private int mark;
    private String notes;
    private StatusEnum status;
    private Date duration;

    public ActivityDoneWtActivitySaveDTO(ActivityDone activityDone, ActivitySaveWtActivityDTO activitySaveWtActivityDTO) {
        this.id = activityDone.getId();
        this.achievement = activityDone.getAchievement();
        this.doneOn = activityDone.getDoneOn();
        this.activitySave = activitySaveWtActivityDTO;
        this.mark = activityDone.getMark();
        this.notes = activityDone.getNotes();
        this.status = activityDone.getStatus();
        this.duration = activityDone.getDuration();
    }
}
