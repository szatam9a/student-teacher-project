package learningprojectbackend.studies.controller.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotBlank(message = "nickname cant be empty")
    private String nickname;
    @NotBlank(message = "password cant be empty")
    private String password;
    @Email(message = "incorrect email address")
    private String email;
    @NotBlank(message = "first name cant be empty")
    private String firstName;
    @NotBlank(message = "last name cant be empty")
    private String lastName;
}
