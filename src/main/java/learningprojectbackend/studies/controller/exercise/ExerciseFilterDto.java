package learningprojectbackend.studies.controller.exercise;

import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExerciseFilterDto {
    private Long id;
    private String title;
    private String description;
    private ExerciseType exerciseType;
}
