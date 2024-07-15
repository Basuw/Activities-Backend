package activities.com.backend.activities.repositories;

import activities.com.backend.activities.models.ActivityDone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityDoneRepository extends CrudRepository<ActivityDone, Long> {
    //List<ActivityDone> findByUserId(long userId);
    ActivityDone findById(long id);

    @Override
    List<ActivityDone> findAll();

    List<ActivityDone> getAllByActivitySave_UserId(long userId);

}
