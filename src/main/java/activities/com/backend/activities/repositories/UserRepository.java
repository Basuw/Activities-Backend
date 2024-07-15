package activities.com.backend.activities.repositories;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String name);
    List<User> findAll();
    User findById(long id);
    //List<ActivitySave> findBy(long userId);
}
