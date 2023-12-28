package activities.com.backend.reminder.repositories;

import activities.com.backend.reminder.models.Activity;
import activities.com.backend.reminder.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByname(String name);
    List<User> findAll();
    User findById(long id);


}