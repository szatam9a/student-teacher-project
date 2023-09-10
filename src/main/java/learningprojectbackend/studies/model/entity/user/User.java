package learningprojectbackend.studies.model.entity.user;

import jakarta.persistence.*;
import learningprojectbackend.studies.model.entity.course.Course;
import learningprojectbackend.studies.model.entity.course.Lesson;
import learningprojectbackend.studies.model.entity.exercise.Exercise;
import learningprojectbackend.studies.model.entity.flashcard.Flashcard;
import learningprojectbackend.studies.model.entity.studerial.Studerial;
import learningprojectbackend.studies.model.entity.tag.Tag;
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
}
