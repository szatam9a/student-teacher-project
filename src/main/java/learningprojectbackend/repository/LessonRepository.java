package learningprojectbackend.repository;

import learningprojectbackend.model.entity.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
