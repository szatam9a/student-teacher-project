package learningprojectbackend.studies.controller.exercise;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import learningprojectbackend.studies.service.entity.exercise.Answer;
import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateExerciseRequest {
    @NotBlank(message = "title cant be empty")
    private String title;
    private String description;
    @NotBlank(message = "exercise type cant be empty")
    @JsonDeserialize(using = ExerciseTypeValidatorDeserializer.class)
    private ExerciseType exerciseType;
    private String task;
    private String question;
    private List<Answer> answers;
}
