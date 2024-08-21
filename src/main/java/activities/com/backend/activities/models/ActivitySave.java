package activities.com.backend.activities.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private float frequency;
    private float objective;
    private Date time;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
