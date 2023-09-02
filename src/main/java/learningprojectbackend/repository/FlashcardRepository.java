package learningprojectbackend.repository;

import learningprojectbackend.model.entity.flashcard.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}
