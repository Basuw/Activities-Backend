package activities.com.backend.activities.dto;

import activities.com.backend.activities.models.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

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
