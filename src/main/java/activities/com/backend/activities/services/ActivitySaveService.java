package activities.com.backend.activities.services;

import activities.com.backend.activities.models.ActivitySave;
import activities.com.backend.activities.models.ActivitySaveGroup;
import activities.com.backend.activities.models.DayEnum;
import activities.com.backend.activities.repositories.ActivityDoneRepository;
import activities.com.backend.activities.repositories.ActivitySaveGroupRepository;
import activities.com.backend.activities.repositories.ActivitySaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitySaveService {

    private final ActivitySaveRepository activitySaveRepository;
    private final ActivitySaveGroupRepository activitySaveGroupRepository;
    private final ActivityDoneRepository activityDoneRepository;

    public ActivitySaveService(ActivitySaveRepository activitySaveRepository,
                               ActivitySaveGroupRepository activitySaveGroupRepository,
                               ActivityDoneRepository activityDoneRepository) {
        this.activitySaveRepository = activitySaveRepository;
        this.activitySaveGroupRepository = activitySaveGroupRepository;
        this.activityDoneRepository = activityDoneRepository;
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
    public void addSave(List<ActivitySave> activitySaveList) {
        // Si les saves transmis ont déjà un groupe (ajout d'un jour à un groupe existant),
        // réutiliser ce groupe — sinon en créer un nouveau.
        ActivitySaveGroup group = activitySaveList.stream()
                .filter(s -> s.getActivitySaveGroup() != null)
                .findFirst()
                .map(s -> activitySaveGroupRepository.findById((long) s.getActivitySaveGroup().getId())
                        .orElseGet(() -> activitySaveGroupRepository.save(new ActivitySaveGroup())))
                .orElseGet(() -> activitySaveGroupRepository.save(new ActivitySaveGroup()));

        for (ActivitySave save : activitySaveList) {
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

    /**
     * Suppression intelligente :
     * - Si des ActivityDones existent pour ce save → soft delete (active = false)
     *   Le save reste en base, les ActivityDones gardent leur lien intact.
     * - Sinon → suppression réelle (pas d'historique à conserver)
     */
    public void deleteSave(long id) {
        boolean hasDones = !activityDoneRepository.getAllByActivitySaveId(id).isEmpty();
        if (hasDones) {
            ActivitySave save = activitySaveRepository.findById(id);
            if (save != null) {
                save.setActive(false);
                activitySaveRepository.save(save);
            }
        } else {
            activitySaveRepository.deleteById(id);
        }
    }

    public List<ActivitySave> getUserSaves(int userId) {
        return activitySaveRepository.findAllByUserId(userId);
    }

    /** Uniquement les saves actifs — pour construire les activités de la journée */
    public List<ActivitySave> getSaveByUserIdAndDay(long userId, DayEnum day) {
        return activitySaveRepository.findAllByUserIdAndDayAndActiveIsTrue(userId, day);
    }

    /** Saves actifs du groupe — pour le modal d'édition (jours déjà configurés) */
    public List<ActivitySave> getSaveByGroupId(long id) {
        return activitySaveRepository.findAllByActivitySaveGroupIdAndActiveIsTrue(id);
    }
}
