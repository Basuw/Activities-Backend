package activities.com.backend.reminder.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "achieve", schema = "reminder")
public class Achieve {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "achieveSequence")
    @SequenceGenerator(name = "achieveSequence", sequenceName = "reminder.achieve_sequence")
    private int id;
    private int actId;
    private int usrId;
    private float achievement;
    private Date dateAchieved;

    public Achieve(int id,int actId,int usrId,float achievement,Date dateAchieved){

        this.id = id;
        this.actId = actId;
        this.usrId = usrId;
        this.achievement = achievement;
        this.dateAchieved = dateAchieved;
    }
}
