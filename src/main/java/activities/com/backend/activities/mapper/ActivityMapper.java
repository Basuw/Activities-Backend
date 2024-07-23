package activities.com.backend.activities.mapper;

import activities.com.backend.activities.dto.ActivityDTO;
import activities.com.backend.activities.dto.ActivityDoneDTO;
import activities.com.backend.activities.models.Activity;
import activities.com.backend.activities.models.ActivityDone;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    @Mapping(source = "user.id", target = "userId")
    ActivityDTO toDto(Activity activity);
}
