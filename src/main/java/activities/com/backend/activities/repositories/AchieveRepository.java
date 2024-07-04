package activities.com.backend.activities.repositories;

import activities.com.backend.activities.models.Achieve;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AchieveRepository extends CrudRepository<Achieve, Long> {
    List<Achieve> findByUsrId(long userId);
    Achieve findById(long id);
    List<Achieve> findAll();


}
