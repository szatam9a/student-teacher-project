package learningprojectbackend.studies.controller.tag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTag {
    private Long id;
    private String backgroundColor;
    private String textColor;
    private String name;
}
