package learningprojectbackend.studies.model.entity.exercise;

import jakarta.persistence.*;
import learningprojectbackend.studies.model.entity.studerial.Studerial;
import learningprojectbackend.studies.model.entity.tag.Tag;
import learningprojectbackend.studies.model.entity.user.User;
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
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Answer> answerList = new LinkedList<>();
    @ManyToMany(mappedBy = "exerciseList")
    private List<Studerial> studerialList;
    @ManyToMany
    @JoinTable(
            name = "exercise_tags",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;
    @ManyToOne
    private User user;

    public void addAnswer(Answer answer) {
        answerList.add(answer);
        answer.setExercise(this);
    }

    public void addAnswer(List<Answer> answerList) {
        answerList.forEach((this::addAnswer));
    }
}
