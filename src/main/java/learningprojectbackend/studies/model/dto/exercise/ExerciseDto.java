package learningprojectbackend.studies.model.dto.exercise;

import learningprojectbackend.studies.model.entity.exercise.ExerciseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDto {
    private long id;
    private String title;
    private ExerciseType exerciseType;

}
