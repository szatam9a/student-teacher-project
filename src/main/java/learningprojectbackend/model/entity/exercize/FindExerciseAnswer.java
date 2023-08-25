package learningprojectbackend.model.entity.exercize;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FindExerciseAnswer extends Answer{
    private int position;
    private String answer;
    private boolean isCorrect;
}
