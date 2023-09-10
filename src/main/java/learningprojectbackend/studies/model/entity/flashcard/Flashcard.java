package learningprojectbackend.studies.model.entity.flashcard;

import jakarta.persistence.*;
import learningprojectbackend.studies.model.entity.studerial.Studerial;
import learningprojectbackend.studies.model.entity.tag.Tag;
import learningprojectbackend.studies.model.entity.user.User;
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
    @JoinTable(
            name = "flashcard_tags",
            joinColumns = @JoinColumn(name = "flashcard_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;
    @ManyToMany(mappedBy = "flashCardList")
    private List<Studerial> studerialList;
    @ManyToOne
    private User user;
}
