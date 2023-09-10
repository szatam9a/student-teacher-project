package learningprojectbackend.studies.model.entity.studerial;

import jakarta.persistence.*;
import learningprojectbackend.studies.model.entity.course.Lesson;
import learningprojectbackend.studies.model.entity.exercise.Exercise;
import learningprojectbackend.studies.model.entity.flashcard.Flashcard;
import learningprojectbackend.studies.model.entity.tag.Tag;
import learningprojectbackend.studies.model.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Studerial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Lesson lesson;
    @ManyToMany
    @JoinTable(
            name = "studyrial_flashcards",
            joinColumns = @JoinColumn(name = "studyrial_id"),
            inverseJoinColumns = @JoinColumn(name = "flashcard_id")
    )
    private List<Flashcard> flashCardList;
    @ManyToMany
    @JoinTable(
            name = "studyrial_exercises",
            joinColumns = @JoinColumn(name = "studyrial_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exerciseList;
    @ManyToMany
    @JoinTable(
            name = "studyrial_tags",
            joinColumns = @JoinColumn(name = "studyrial_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;
    @ManyToOne
    private User user;
}
