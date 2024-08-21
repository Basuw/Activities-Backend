package activities.com.backend.activities.repositories;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.DayEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivitySaveRepository extends CrudRepository<ActivitySave, Long> {
    //List<ActivitySave> findByUserId(long userId);
    ActivitySave findById(long id);
    List<ActivitySave> findAll();

    List<ActivitySave> findAllByUserId(int userId);

    List<ActivitySave> findAllByUserIdAndDay(long userId, DayEnum day);
}
