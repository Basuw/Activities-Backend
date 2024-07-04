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
@Table(name = "User", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "userSequence")
    @SequenceGenerator(name = "userSequence", sequenceName = "public.activity_save_sequence")
    private int id;
    private String name;
    private String password;
    private String mail;
    private String role;
    private Date createdOn;
    private Date birthDate;
    private float weight;
    private float height;
    private float fat;
    private float targetWeight;

    public User(int id, String name, String password, String mail, String role, Date createdOn, Date birthDate, float weight, float height, float fat, float targetWeight){
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.role = role;
        this.createdOn = createdOn;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.fat = fat;
        this.targetWeight = targetWeight;
    }
}
