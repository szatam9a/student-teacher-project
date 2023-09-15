package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    //@Query("select t from Tag t where t.user.id = ?1")
    List<Tag> findAllByUserId(Long userId);

}
