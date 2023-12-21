package activities.com.backend.reminder.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "activity", schema = "reminder")
public class Activity {
    @Id
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
