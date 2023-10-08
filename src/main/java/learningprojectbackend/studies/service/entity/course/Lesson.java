package learningprojectbackend.studies.service.entity.course;

import jakarta.persistence.*;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "lesson")
    private Set<Studerial> studerials;
    @ManyToMany(mappedBy = "lessons")
    private Set<Course> courses;
    @ManyToMany
    @JoinTable(
            name = "lesson_tags",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
    @ManyToOne
    private User user;

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
