package activities.com.backend.activities.dto;

import activities.com.backend.activities.models.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ActivityProgressDTO {
    private ActivityDoneDTO activityDone;
    private double weekProgress;
    private double weekObjective;
}
