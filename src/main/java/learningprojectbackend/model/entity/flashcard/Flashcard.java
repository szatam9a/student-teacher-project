package learningprojectbackend.model.entity.flashcard;

import jakarta.persistence.*;
import learningprojectbackend.model.entity.studyrial.Studyrial;
import learningprojectbackend.model.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flashCardFront;
    private String flashCardBack;
    @ManyToMany
    private List<Tag> tagList;
    @ManyToMany(mappedBy = "flashCardList")
    private List<Studyrial> studyrialList;
}
