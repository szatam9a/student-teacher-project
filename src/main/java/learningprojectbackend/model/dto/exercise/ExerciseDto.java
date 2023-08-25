package learningprojectbackend.model.dto.exercise;

import learningprojectbackend.model.ExerciseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDto {
    private long id;
    private String title;
    private ExerciseType exerciseType;
}
