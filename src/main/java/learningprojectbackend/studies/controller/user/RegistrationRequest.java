package learningprojectbackend.studies.controller.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import learningprojectbackend.studies.service.entity.user.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    @JsonDeserialize(using = GenderValidateDeserializer.class)
    private Gender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    @NotBlank(message = "are you sure you aint no robot")
    private String recaptcha;
}
