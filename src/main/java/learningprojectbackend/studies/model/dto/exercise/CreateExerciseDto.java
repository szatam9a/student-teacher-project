package learningprojectbackend.studies.model.dto.exercise;

import learningprojectbackend.studies.model.entity.exercise.Answer.ExerciseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseDto {
    private String title;
    private ExerciseType exerciseType;
}
