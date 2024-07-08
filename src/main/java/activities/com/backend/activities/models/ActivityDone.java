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
@Table(name = "activity_done", schema = "activities")
@SequenceGenerator(name = "achieveSequence", sequenceName = "activities.activity_done_sequence", allocationSize=1)
public class ActivityDone {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "achieveSequence")
    private int id;
    private int activitySaveId;
    private float achievement;
    private Date doneOn;

}
