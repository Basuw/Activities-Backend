package activities.com.backend.activities.repositories;

import activities.com.backend.activities.models.Save;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaveRepository extends CrudRepository<Save, Long> {
    List<Save> findByUsrId(long userId);
    Save findById(long id);
    List<Save> findAll();


}
