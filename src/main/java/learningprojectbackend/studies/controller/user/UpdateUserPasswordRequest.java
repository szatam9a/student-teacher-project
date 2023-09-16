package learningprojectbackend.studies.controller.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserPasswordRequest {
    @NotBlank
    private String password;
}
