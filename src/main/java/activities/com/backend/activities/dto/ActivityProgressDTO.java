package activities.com.backend.activities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivityProgressDTO {
    private ActivityDoneWtActivitySaveDTO activityDone;
    private double weekProgress;
    private double weekObjective;
}
