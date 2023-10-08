package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    //@Query("select t from Tag t where t.user.id = ?1")
    List<Tag> findAllByUserId(Long userId);


    @Query("select t from Tag t where t.id in :ids and t.user.id = :userId")
//    @EntityGraph(attributePaths = {"courses", "lessons"})
    List<Tag> findAllByIdInAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
