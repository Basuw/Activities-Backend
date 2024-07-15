package activities.com.backend.activities.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "activity", schema = "activities")
@SequenceGenerator(name = "activitySequence", sequenceName = "activities.activity_sequence", allocationSize=1)
public class Activity {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "activitySequence")
    private int id;
    private String name;
    private String description;
    private String unity;
    private String icon;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private List<ActivitySave> activitySaves;

}
