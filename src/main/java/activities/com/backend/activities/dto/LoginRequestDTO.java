package activities.com.backend.activities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDTO {
    private String mail;
    private String password; // SHA-256 hex hash
}
