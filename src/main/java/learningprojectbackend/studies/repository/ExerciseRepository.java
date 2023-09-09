package learningprojectbackend.studies.repository;


import learningprojectbackend.studies.model.entity.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
