package learningprojectbackend.studies.controller.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String nickname;
    private String email;
    private String lastName;
    private String firstName;
}
