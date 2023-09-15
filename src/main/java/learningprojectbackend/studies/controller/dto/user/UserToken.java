package learningprojectbackend.studies.controller.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToken {
    private String token;
    private UserDto user;
}
