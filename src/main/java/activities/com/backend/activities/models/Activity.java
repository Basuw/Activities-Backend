package activities.com.backend.activities.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Activity", schema = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "activitySequence")
    @SequenceGenerator(name = "activitySequence", sequenceName = "activities.activity_sequence")
    private int id;
    private String name;
    private String description;
    private String unity;
    private String icon;

    public Activity(int id, String name, String description, String unity, String icon){
        this.id=id;
        this.name = name;
        this.description = description;
        this.unity = unity;
        this.icon = icon;
    }
}
