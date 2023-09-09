package learningprojectbackend.studies.model.entity.course;

import jakarta.persistence.*;
import learningprojectbackend.studies.model.entity.studyrial.Studyrial;
import learningprojectbackend.studies.model.entity.tag.Tag;
import learningprojectbackend.studies.model.entity.user.User;
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
    @JoinTable(
            name = "lesson_tags",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;
    @OneToMany(mappedBy = "lesson")
    private List<Studyrial> studyrialList;
    @ManyToOne
    private Course course;
    @ManyToOne
    private User user;
}
