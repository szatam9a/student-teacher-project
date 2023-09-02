package learningprojectbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
