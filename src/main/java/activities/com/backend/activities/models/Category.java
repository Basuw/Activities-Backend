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
@Table(name = "category", schema = "activities")
@SequenceGenerator(name = "categorySequence", sequenceName = "activities.categorySequence", allocationSize=1)
public class Category {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "categorySequence")
    private int id;
    private String name;
    private String description;
    private String icon;
}
