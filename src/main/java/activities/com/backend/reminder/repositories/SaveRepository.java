package activities.com.backend.reminder.repositories;

import activities.com.backend.reminder.models.Achieve;
import activities.com.backend.reminder.models.Save;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaveRepository extends CrudRepository<Save, Long> {
    List<Save> findByUsrId(long userId);
    Save findById(long id);
    List<Save> findAll();


}