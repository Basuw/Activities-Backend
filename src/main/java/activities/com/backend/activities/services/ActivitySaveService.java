package activities.com.backend.activities.services;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.ActivitySaveGroup;
import activities.com.backend.activities.models.DayEnum;
import activities.com.backend.activities.repositories.ActivitySaveGroupRepository;
import activities.com.backend.activities.repositories.ActivitySaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitySaveService {

    private final ActivitySaveRepository activitySaveRepository;
    private final ActivitySaveGroupRepository activitySaveGroupRepository;

    public ActivitySaveService(ActivitySaveRepository activitySaveRepository,
                               ActivitySaveGroupRepository activitySaveGroupRepository) {
        this.activitySaveRepository = activitySaveRepository;
        this.activitySaveGroupRepository = activitySaveGroupRepository;
    }

    public List<ActivitySave> getAllSave() {
        return activitySaveRepository.findAll();
    }

    public ActivitySave getSaveById(long id) {
        return activitySaveRepository.findById(id);
    }

    /**
     * Crée un ActivitySave.
     * Si aucun ActivitySaveGroup n'est fourni, un nouveau groupe est créé automatiquement.
     * Cela permet de regrouper plusieurs ActivitySave (ex : même activité sur plusieurs jours)
     * en passant le même activitySaveGroup dans le body.
     */
    public void addSave(List<ActivitySave> activitySave) {
        ActivitySaveGroup group = activitySaveGroupRepository.save(new ActivitySaveGroup());
        for (ActivitySave save : activitySave) {
            save.setActivitySaveGroup(group);
            activitySaveRepository.save(save);
        }
    }

    /**
     * Met à jour partiellement un ActivitySave existant (PATCH).
     * Seuls les champs non-nuls / non-zéro du patch sont appliqués.
     */
    public void updateSave(long id, ActivitySave patch) {
        ActivitySave existing = activitySaveRepository.findById(id);
        if (existing == null) {
            throw new RuntimeException("ActivitySave not found with id: " + id);
        }
        if (patch.getFrequency() != 0)  existing.setFrequency(patch.getFrequency());
        if (patch.getObjective() != 0)  existing.setObjective(patch.getObjective());
        if (patch.getNotes() != null)    existing.setNotes(patch.getNotes());
        if (patch.getDay() != null)      existing.setDay(patch.getDay());
        activitySaveRepository.save(existing);
    }

    public void deleteSave(long id) {
        activitySaveRepository.deleteById(id);
    }

    public List<ActivitySave> getUserSaves(int userId) {
        return activitySaveRepository.findAllByUserId(userId);
    }

    public List<ActivitySave> getSaveByUserIdAndDay(long userId, DayEnum day) {
        return activitySaveRepository.findAllByUserIdAndDay(userId, day);
    }

    public List<ActivitySave> getSaveByGroupId(long id) {
        return activitySaveRepository.findAllByActivitySaveGroupId(id);
    }
}
