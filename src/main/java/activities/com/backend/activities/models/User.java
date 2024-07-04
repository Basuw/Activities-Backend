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
@Table(name = "user", schema = "reminder")
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "userSequence")
    @SequenceGenerator(name = "userSequence", sequenceName = "reminder.user_sequence")
    private int id;
    private String name;
    private String password;
}
