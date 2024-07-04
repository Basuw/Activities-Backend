package activities.com.backend.activities.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ActivitySave", schema = "activities")
public class ActivitySave {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "savedSequence")
    @SequenceGenerator(name = "savedSequence", sequenceName = "activities.activity_save_sequence")
    private int id;
    private int activityId;
    private int userId;
    private float frequency;
    private float objective;

    public ActivitySave(int id, int activityId, int userId, float frequency, float objective){
        this.id = id;
        this.activityId = activityId;
        this.userId = userId;
        this.frequency = frequency;
        this.objective = objective;
    }
}
