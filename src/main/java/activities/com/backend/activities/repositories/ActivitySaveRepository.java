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

    /** Uniquement les saves actifs — utilisé pour construire les activités de la journée */
    List<ActivitySave> findAllByUserIdAndDayAndActiveIsTrue(long userId, DayEnum day);

    List<ActivitySave> findAllByActivitySaveGroupId(long id);

    /** Saves actifs du groupe — utilisé pour le modal d'édition */
    List<ActivitySave> findAllByActivitySaveGroupIdAndActiveIsTrue(long id);
}
