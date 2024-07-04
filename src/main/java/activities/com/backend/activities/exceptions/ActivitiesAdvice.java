package activities.com.backend.activities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ActivitiesAdvice {
    @ResponseBody
    @ExceptionHandler(ActivitiesExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String actHandler(ActivitiesExceptions ex) {
        return ex.getMessage();
    }
}
