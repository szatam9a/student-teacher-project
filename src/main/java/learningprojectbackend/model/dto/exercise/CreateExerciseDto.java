package learningprojectbackend.model.dto.exercise;

import learningprojectbackend.model.ExerciseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseDto {
    private String title;
    private ExerciseType exerciseType;
}
