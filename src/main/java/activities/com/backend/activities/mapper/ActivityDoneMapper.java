package activities.com.backend.activities.mapper;

import activities.com.backend.activities.dto.ActivityDoneDTO;
import activities.com.backend.activities.models.ActivityDone;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface ActivityDoneMapper {
    ActivityDoneMapper INSTANCE = Mappers.getMapper(ActivityDoneMapper.class);

    @Mapping(source = "activitySave.id", target = "activitySaveId")
    ActivityDoneDTO toDto(ActivityDone activityDone);
}
