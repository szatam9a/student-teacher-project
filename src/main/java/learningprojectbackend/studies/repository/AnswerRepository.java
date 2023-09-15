package learningprojectbackend.studies.repository;


import learningprojectbackend.studies.service.entity.exercise.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
