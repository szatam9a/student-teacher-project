package learningprojectbackend.studies.controller.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTagRequest {
    private Long id;
    @NotBlank(message = "background color cant be empty")
    private String backgroundColor;
    @NotBlank(message = "text color cant be empty")
    private String textColor;
    @NotBlank(message = "tag name cant be empty")
    private String name;
}
