package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.course.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {


    //    @Query("select l from Lesson l left join fetch l.tags where l.id in :ids and l.user.id = :userId")
    @EntityGraph(attributePaths = {"tags", "studerials"})
    List<Lesson> findAllByIdInAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);

    //    @EntityGraph(attributePaths = {"tags", "studerials"})
    Page<Lesson> findAllByUserId(Long id, Pageable pageable);
}
