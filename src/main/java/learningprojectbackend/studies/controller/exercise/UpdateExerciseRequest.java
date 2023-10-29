package learningprojectbackend.studies.controller.exercise;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import learningprojectbackend.studies.service.entity.exercise.Answer;
import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateExerciseRequest {
    @NotBlank(message = "title cant be empty")
    private String title;
    private String description;
    private String leftCategory;
    private String rightCategory;
    private String task;
    private String question;
    @NotBlank(message = "exercise type cant be empty")
    @JsonDeserialize(using = ExerciseTypeValidatorDeserializer.class)
    private ExerciseType exerciseType;
    private List<Answer> answers;
}
