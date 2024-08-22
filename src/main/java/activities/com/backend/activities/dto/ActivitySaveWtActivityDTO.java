package activities.com.backend.activities.dto;

import activities.com.backend.activities.models.ActivitySave;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ActivitySaveWtActivityDTO {
    private int id;
    private float frequency;
    private float objective;
    private ActivityDTO activity;
    private int userId;

    public ActivitySaveWtActivityDTO(ActivitySave activitySave, ActivityDTO activityDTO) {
        this.id = activitySave.getId();
        this.frequency = activitySave.getFrequency();
        this.objective = activitySave.getObjective();
        this.activity = activityDTO;
        this.userId = activitySave.getUser().getId();
    }
}
