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
@Table(name = "saved", schema = "reminder")
public class Save {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "savedSequence")
    @SequenceGenerator(name = "savedSequence", sequenceName = "reminder.saved_sequence")
    private int id;
    private int actId;
    private int usrId;
    private float frequence;
    private Timestamp time;

    public Save(int id, int actId, int usrId, float frequence,Timestamp time){

        this.id = id;
        this.actId = actId;
        this.usrId = usrId;
        this.frequence = frequence;
        this.time = time;
    }
}
