package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {


    @Query("select l from Lesson l where l.id in :ids and l.user.id = :userId")
    List<Lesson> findAllByIdInAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
