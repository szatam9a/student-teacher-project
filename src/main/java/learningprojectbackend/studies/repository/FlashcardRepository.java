package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.flashcard.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}
