package learningprojectbackend.model.entity.course;

import jakarta.persistence.*;
import learningprojectbackend.model.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "course")
    private List<Lesson> lessonList;
    @ManyToMany
    private List<Tag> taglist;
}
