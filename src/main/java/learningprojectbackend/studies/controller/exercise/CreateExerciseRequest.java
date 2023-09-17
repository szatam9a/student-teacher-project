package learningprojectbackend.studies.controller.exercise;

import jakarta.validation.constraints.NotBlank;
import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;

@Getter
public class CreateExerciseRequest {
    @NotBlank(message = "title cant be empty")
    private String title;
    @NotBlank(message = "exercise type cant be empty")
    private ExerciseType exerciseType;
}
