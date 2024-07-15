package activities.com.backend.activities.common;

import activities.com.backend.activities.dto.ErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler{

    /**
     * Handle EntityNotFoundException.
     *
     * @param ex
     *          The entity not found exception.
     * @return
     *          The errorDTO.
     */
    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ResponseEntity<ErrorDTO> handleNotFoundException(EntityNotFoundException ex){
        ErrorDTO dto = new ErrorDTO();
        dto.setCode(404);
        dto.setException("EntityNotFoundException");
        dto.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorDTO>(dto, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle Server error.
     *
     * @param ex
     *          The exception.
     * @return
     *          The errorDTO.
     */
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorDTO> handleServorError(Exception ex){
        ErrorDTO dto = new ErrorDTO();

        dto.setCode(500);
        dto.setException(ex.getClass().getName());
        dto.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

