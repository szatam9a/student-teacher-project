package learningprojectbackend.studies.controller.exercise;

import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExerciseDto {
    private long id;
    private String title;
    private String description;
    private String leftCategory;
    private String rightCategory;
    private String task;
    private String question;
    private ExerciseType exerciseType;
    private List<AnswerDto> answers;

}
