package activities.com.backend.activities.repositories;

import activities.com.backend.activities.models.ActivityDone;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ActivityDoneRepository extends CrudRepository<ActivityDone, Long> {
    //List<ActivityDone> findByUserId(long userId);
    ActivityDone findById(long id);

    @Override
    List<ActivityDone> findAll();

    List<ActivityDone> getAllByActivitySave_UserId(long userId);

    List<ActivityDone> getAllByActivitySaveId(long activitySaveId);

    List<ActivityDone> getAllByActivitySaveIdAndDoneOnIsGreaterThanEqual(long activitySaveId, Date date);

    List<ActivityDone> getAllByActivitySaveIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(long activitySaveId, Date begindate, Date enddate);

    List<ActivityDone> getAllByActivitySave_UserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(long userId, Date begindate, Date enddate);

    List<ActivityDone> getAllByActivitySave_UserIdAndDoneOn(long userId, Date date);

    List<ActivityDone> getAllByActivitySaveActivityIdAndActivitySaveUserIdAndDoneOnIsGreaterThanEqualAndDoneOnIsLessThan(long activityId, long userId, Date begindate, Date enddate);
}
