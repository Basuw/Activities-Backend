package activities.com.backend.reminder.repositories;

import activities.com.backend.reminder.models.Achieve;
import activities.com.backend.reminder.models.Activity;
import activities.com.backend.reminder.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AchieveRepository extends CrudRepository<Achieve, Long> {
    List<Achieve> findByUsrId(long userId);
    Achieve findById(long id);
    List<Achieve> findAll();


}