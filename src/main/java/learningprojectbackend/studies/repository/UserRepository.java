package learningprojectbackend.studies.repository;

import learningprojectbackend.studies.service.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    @Query("select u from User u left join fetch u.exerciseList where u.id = :userId")
    User findByIdWithExercises(Long userId);

    @Query("select u from User u left join fetch u.tagList where u.id = :userId")
    User findByIdWithTags(Long userId);
}
