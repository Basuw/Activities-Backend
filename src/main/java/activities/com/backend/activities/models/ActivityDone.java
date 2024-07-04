package activities.com.backend.activities.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ActivityDone", schema = "activities")
public class ActivityDone {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "achieveSequence")
    @SequenceGenerator(name = "achieveSequence", sequenceName = "activities.activity_done_sequence")
    private int id;
    private int activityDoneId;
    private float achievement;
    private Date doneOn;

    public ActivityDone(int id, int activityDoneId, float achievement, Date doneOn){
        this.id = id;
        this.activityDoneId = activityDoneId;
        this.achievement = achievement;
        this.doneOn = doneOn;
    }
}
