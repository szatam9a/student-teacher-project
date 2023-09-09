package learningprojectbackend.studies.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "username cant be empty")
    private String username;
    @NotBlank(message = "password cant be empty")
    private String password;
    @Email(message = "incorrect email address")
    private String email;
    @NotBlank(message = "first name cant be empty")
    private String firstName;
    @NotBlank(message = "last name cant be empty")
    private String lastName;
}
