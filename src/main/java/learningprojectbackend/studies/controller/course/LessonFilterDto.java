package learningprojectbackend.studies.controller.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonFilterDto {
    private Long id;
    private String title;
    private int coursesNumber;
}
