package learningprojectbackend.studies.repository;


import learningprojectbackend.studies.service.entity.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
