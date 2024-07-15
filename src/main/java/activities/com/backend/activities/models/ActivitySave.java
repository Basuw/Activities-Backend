package activities.com.backend.activities.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_save", schema = "activities")
@SequenceGenerator(name = "savedSequence", sequenceName = "activities.activity_save_sequence", allocationSize=1)
public class ActivitySave {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "savedSequence")
    private int id;
    //private int activityId;
    private float frequency;
    private float objective;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_save_id", referencedColumnName = "id")
    private List<ActivityDone> activityDones;
}
