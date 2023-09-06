package learningprojectbackend.model.entity.exercise.Answer;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MatchPairExerciseAnswer extends Answer {
    private String leftPair;
    private String rightPair;
}
