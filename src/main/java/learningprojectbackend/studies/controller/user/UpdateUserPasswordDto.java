package learningprojectbackend.studies.controller.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserPasswordDto {
    @NotBlank
    private String password;
}
