package learningprojectbackend.model.entity.user;

import jakarta.persistence.*;
import learningprojectbackend.model.entity.course.Course;
import learningprojectbackend.model.entity.course.Lesson;
import learningprojectbackend.model.entity.exercise.Exercise;
import learningprojectbackend.model.entity.flashcard.Flashcard;
import learningprojectbackend.model.entity.studyrial.Studyrial;
import learningprojectbackend.model.entity.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    //    @Embedded
//    private UserDetails userDetails;
    private String roles;
    @OneToMany(mappedBy = "user")
    private List<Course> courseList;
    @OneToMany(mappedBy = "user")
    private List<Lesson> lessonList;
    @OneToMany(mappedBy = "user")
    private List<Studyrial> studyrialList;
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
}
