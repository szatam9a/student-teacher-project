package learningprojectbackend.studies.controller.exercise;

import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;

@Getter
public class CreateExerciseDto {
    private String title;
    private ExerciseType exerciseType;
}
