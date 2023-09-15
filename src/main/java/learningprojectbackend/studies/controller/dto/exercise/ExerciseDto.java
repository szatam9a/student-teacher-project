package learningprojectbackend.studies.controller.dto.exercise;

import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDto {
    private long id;
    private String title;
    private ExerciseType exerciseType;

}
