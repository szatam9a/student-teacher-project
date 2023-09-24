package learningprojectbackend.studies.service.entity.user;

import jakarta.persistence.*;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.exercise.Exercise;
import learningprojectbackend.studies.service.entity.flashcard.Flashcard;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nickname;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dateOfBirth;
    private String roles;
    @OneToMany(mappedBy = "user")
    private List<Course> courseList;
    @OneToMany(mappedBy = "user")
    private List<Lesson> lessonList;
    @OneToMany(mappedBy = "user")
    private List<Studerial> studerialList;
    @OneToMany(mappedBy = "user")
    private List<Flashcard> flashcardList;
    @OneToMany(mappedBy = "user")
    private List<Exercise> exerciseList;
    @OneToMany(mappedBy = "user")
    private List<Tag> tagList;

    public User addExercise(Exercise exercise) {
        exerciseList.add(exercise);
        exercise.setUser(this);
        return this;
    }

    public User addTag(Tag tag) {
        tagList.add(tag);
        tag.setUser(this);
        return this;
    }
}
