package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
