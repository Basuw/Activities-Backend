package activities.com.backend.activities.services;

import activities.com.backend.activities.models.DayEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class CalendarService {
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

    public CalendarService() {
    }

    public DayEnum getDayFromDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DayEnum day = DayEnum.values()[calendar.get(Calendar.DAY_OF_WEEK)];
        LOGGER.info("DayOFWeek : {}", Calendar.DAY_OF_WEEK);
        LOGGER.info("calendar.get(Calendar.DAY_OF_WEEK) : {}", calendar.get(Calendar.DAY_OF_WEEK));
        return day;
    }
}
