package learningprojectbackend.model.entity.exercise.Answer;

import jakarta.persistence.*;
import learningprojectbackend.model.entity.exercise.Exercise;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Exercise exercise;
    private String sentenceBeginning;
    private String sentenceEnding;
    private List<String> correctAnswers;
    private int position;
    private String answer;
    private boolean isCorrect;
    private String leftPair;
    private String rightPair;
}
