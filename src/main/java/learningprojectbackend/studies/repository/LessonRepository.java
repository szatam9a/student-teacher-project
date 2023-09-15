package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
