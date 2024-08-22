package activities.com.backend.activities.dto;

import activities.com.backend.activities.models.DayEnum;

public class ActivitySaveDTO {
    private int id;
    private float frequency;
    private float objective;
    private int activityId;
    private DayEnum day;
    private String notes;
    private int userId;
}
