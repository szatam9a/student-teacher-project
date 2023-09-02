package learningprojectbackend.model.entity.studyrial;

import jakarta.persistence.*;
import learningprojectbackend.model.entity.course.Lesson;
import learningprojectbackend.model.entity.exercise.Exercise;
import learningprojectbackend.model.entity.flashcard.Flashcard;
import learningprojectbackend.model.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Studyrial {
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
}
