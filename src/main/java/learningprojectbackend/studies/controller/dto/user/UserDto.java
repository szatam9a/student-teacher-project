package learningprojectbackend.studies.controller.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String nickname;
    private String email;
    private String firstName;
    private String lastName;
}
