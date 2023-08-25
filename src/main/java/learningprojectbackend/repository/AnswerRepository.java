package learningprojectbackend.repository;

import learningprojectbackend.model.entity.exercize.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
