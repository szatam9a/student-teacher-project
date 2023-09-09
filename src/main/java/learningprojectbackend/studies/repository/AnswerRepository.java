package learningprojectbackend.studies.repository;


import learningprojectbackend.studies.model.entity.exercise.Answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
