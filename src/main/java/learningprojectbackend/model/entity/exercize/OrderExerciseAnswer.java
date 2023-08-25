package learningprojectbackend.model.entity.exercize;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderExerciseAnswer extends Answer {
    public int position;
    public String answer;
}
