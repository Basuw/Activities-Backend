package activities.com.backend.activities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorDTO {

    /**
     * The code error.
     */
    @Schema(description = "The error code.")
    private int code;

    /**
     * The exception type.
     */
    @Schema(description = "The exception type.")
    private String exception;

    /**
     * The error message.
     */
    @Schema(description = "The error message.")
    private String message;
}
