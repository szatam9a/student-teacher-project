package learningprojectbackend.studies.controller.exercise;

import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;

@Getter
public class CreateExerciseRequest {
    private String title;
    private ExerciseType exerciseType;
}
