package learningprojectbackend.repository;


import learningprojectbackend.model.entity.exercise.Answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
