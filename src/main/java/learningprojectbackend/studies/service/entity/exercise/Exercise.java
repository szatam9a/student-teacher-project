package learningprojectbackend.studies.service.entity.exercise;

import jakarta.persistence.*;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import learningprojectbackend.studies.service.entity.user.User;
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
