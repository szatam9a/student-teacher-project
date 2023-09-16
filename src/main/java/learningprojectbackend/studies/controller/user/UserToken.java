package learningprojectbackend.studies.controller.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private String token;
    private UserDto user;
}
