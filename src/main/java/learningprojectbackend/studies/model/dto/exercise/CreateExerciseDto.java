package learningprojectbackend.studies.model.dto.exercise;

import learningprojectbackend.studies.model.entity.exercise.ExerciseType;
import lombok.Getter;

@Getter
public class CreateExerciseDto {
    private String title;
    private ExerciseType exerciseType;
}
