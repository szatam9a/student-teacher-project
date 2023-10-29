package learningprojectbackend.studies.service.entity.exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private int position;
    private String answer;
    private boolean correct;
    private String leftPair;
    private String rightPair;
}
