package activities.com.backend.reminder.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "activity", schema = "reminder")
public class Activity {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "activitySequence")
    @SequenceGenerator(name = "activitySequence", sequenceName = "reminder.activity_sequence")
    private int id;
    private String name;
    private String description;
    private String unit;
    private String icon;
    public Activity(int id, String name, String description, String unit, String icon){
        this.id=id;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.icon = icon;
    }
}
