package learningprojectbackend.studies.controller.studerial;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateStuderialRequest {
    @NotBlank(message = "title cant be empty")
    private String title;
}
