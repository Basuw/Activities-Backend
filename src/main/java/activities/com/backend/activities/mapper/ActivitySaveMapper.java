package activities.com.backend.activities.mapper;

import activities.com.backend.activities.dto.ActivitySaveDTO;
import activities.com.backend.activities.mapper.common.Mapper;
import activities.com.backend.activities.models.ActivitySave;

@org.mapstruct.Mapper(componentModel = "spring")
public abstract class ActivitySaveMapper implements Mapper<ActivitySaveDTO, ActivitySave> {

/*    @Override
    @Mapping(source = "activity.id", target="activityId")
    public abstract ActivitySaveDTO toDto(ActivitySave entity);*/
/*    @Override
    @Mapping(target = "activity", source = "dto.activityId")
    public abstract ActivitySave toEntity(ActivitySaveDTO dto);*/
}
