package learningprojectbackend.studies.repository;


import learningprojectbackend.studies.service.entity.exercise.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>, JpaSpecificationExecutor<Exercise> {
    Page<Exercise> findAllByUserId(Long id, Pageable pageable);
}
