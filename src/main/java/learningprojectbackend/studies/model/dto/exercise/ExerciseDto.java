package learningprojectbackend.studies.model.dto.exercise;

import learningprojectbackend.studies.model.entity.exercise.Answer.ExerciseType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExerciseDto {
    private long id;
    private String title;
    private ExerciseType exerciseType;
    private List<AnswerDto> answerList;
}
