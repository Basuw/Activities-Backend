package activities.com.backend.activities.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_save_group", schema = "activities")
@SequenceGenerator(name = "savedGroupSequence", sequenceName = "activities.activity_save_group_sequence", allocationSize=1)
public class ActivitySaveGroup {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "savedGroupSequence")
    private int id;
}
