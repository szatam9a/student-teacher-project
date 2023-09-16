package learningprojectbackend.studies.controller.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
    private String firstName;
    private String lastName;
}
