package learningprojectbackend.studies.controller.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserPasswordDto {
    @NotBlank
    private String password;
}
