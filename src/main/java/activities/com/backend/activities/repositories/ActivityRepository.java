package activities.com.backend.activities.repositories;

import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findAll();
    Activity findById(long id);

    List<Activity> findAllByUser(User user);


}
