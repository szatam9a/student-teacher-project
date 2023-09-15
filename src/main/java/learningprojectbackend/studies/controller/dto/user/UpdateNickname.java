package learningprojectbackend.studies.controller.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class UpdateNickname {
    @NotBlank
    private String nickname;
}
