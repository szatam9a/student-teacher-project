package learningprojectbackend.model.entity.course;

import jakarta.persistence.*;
import learningprojectbackend.model.entity.studyrial.Studyrial;
import learningprojectbackend.model.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany
    private List<Tag> tagList;
    @OneToMany(mappedBy = "lesson")
    private List<Studyrial> studyrialList;
    @ManyToOne
    private Course course;
}