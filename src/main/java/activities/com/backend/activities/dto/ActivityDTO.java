package activities.com.backend.activities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivityDTO {
    private int id;
    private String name;
    private String description;
    private String unity;
    private String icon;
    private int userId;
}
