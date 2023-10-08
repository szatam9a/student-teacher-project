package learningprojectbackend.studies.service.entity.tag;

import jakarta.persistence.*;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.exercise.Exercise;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String backgroundColor;
    private String textColor;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<Studerial> studerials;
    @ManyToMany(mappedBy = "tags")
    private Set<Exercise> exercises;
    @ManyToMany(mappedBy = "tags")
    private Set<Course> courses;
    @ManyToMany(mappedBy = "tags")
    private Set<Lesson> lessons;
    @ManyToOne
    private User user;

    public void addStuderial(Studerial studerial) {
        studerials.add(studerial);
    }

    public void removeStuderial(Studerial studerial) {
        studerials.remove(studerial);
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
