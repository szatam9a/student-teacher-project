package learningprojectbackend.auth.model.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
}
