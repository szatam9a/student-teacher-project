package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.model.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
