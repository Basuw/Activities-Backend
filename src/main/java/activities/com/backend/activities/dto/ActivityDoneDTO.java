package activities.com.backend.activities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ActivityDoneDTO {
    private int id;
    private float achievement;
    private Date doneOn;
    private int activitySaveId;
}
