package learningprojectbackend.studies.service.entity.flashcard;

import jakarta.persistence.*;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
    private Set<Tag> tags;
    @ManyToMany(mappedBy = "flashcards")
    private Set<Studerial> studerials;
    @ManyToOne
    private User user;
}
