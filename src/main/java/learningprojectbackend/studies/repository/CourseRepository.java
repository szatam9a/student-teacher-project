package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    //    @Query("select c from Course c where c.user.id = ?1")
//    @EntityGraph(attributePaths = {"lessons", "tags"})
    Page<Course> findAllByUserId(Long userid, Pageable pageable);

    //    @Query("select c from Course c left join fetch c.lessons l left join fetch l.studerials where c.id = ?1")
//    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.lessons l LEFT JOIN FETCH l.studerials LEFT JOIN FETCH c.tags WHERE c.id = :courseId")
//    @EntityGraph(attributePaths = {"lessons", "tags"})
    Optional<Course> findById(@Param("courseId") Long id);
}
