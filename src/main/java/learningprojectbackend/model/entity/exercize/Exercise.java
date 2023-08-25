package learningprojectbackend.model.entity.exercize;

import jakarta.persistence.*;
import learningprojectbackend.model.ExerciseType;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
@Entity
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;
    @OneToMany( mappedBy = "exercise" , cascade = CascadeType.ALL)
    private List<Answer> answerList = new LinkedList<>();

    public void addAnswer(Answer answer){
        answerList.add(answer);
        answer.setExercise(this);
    }
    public void addAnswer(List<Answer> answerList){
        answerList.forEach((this::addAnswer));
    }
}
