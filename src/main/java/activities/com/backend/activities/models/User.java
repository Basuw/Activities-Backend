package activities.com.backend.activities.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "userSequence")
    @SequenceGenerator(name = "userSequence", sequenceName = "public.user_sequence", allocationSize = 1)
    private int id;
    private String username;
    private String password;
    private String mail;
    private String role;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdOn;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private float weight;
    private float height;
    private float fat;
    private float targetWeight;
}
