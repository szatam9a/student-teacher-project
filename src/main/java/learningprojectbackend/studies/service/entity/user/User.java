package learningprojectbackend.studies.service.entity.user;

import jakarta.persistence.*;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.exercise.Exercise;
import learningprojectbackend.studies.service.entity.flashcard.Flashcard;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
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
    private Set<Course> courses;
    @OneToMany(mappedBy = "user")
    private Set<Lesson> lessons;
    @OneToMany(mappedBy = "user")
    private Set<Studerial> studerials;
    @OneToMany(mappedBy = "user")
    private Set<Flashcard> flashcards;
    @OneToMany(mappedBy = "user")
    private Set<Exercise> exercises;
    @OneToMany(mappedBy = "user")
    private Set<Tag> tags;

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setUser(this);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.setUser(this);
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
