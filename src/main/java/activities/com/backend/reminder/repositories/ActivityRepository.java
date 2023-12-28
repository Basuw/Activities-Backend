package activities.com.backend.reminder.repositories;

import activities.com.backend.reminder.models.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findByname(String name);
    List<Activity> findAll();
    Activity findById(long id);


}