package learningprojectbackend.studies.controller.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCourseRequest {
    @NotBlank(message = "title cant be empty")
    private String title;
}
