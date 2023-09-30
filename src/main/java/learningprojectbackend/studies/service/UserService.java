package learningprojectbackend.studies.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.auth.exception.EmailAddressIsTakenException;
import learningprojectbackend.auth.exception.UserNotFoundException;
import learningprojectbackend.studies.controller.user.UpdateUserPasswordRequest;
import learningprojectbackend.studies.repository.UserRepository;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        User userToRegsiter = new User();
        userToRegsiter.setNickname("admin");
        userToRegsiter.setPassword("admin");
        userToRegsiter.setEmail("smithy@admin.com");
        userToRegsiter.setFirstName("Tom");
        userToRegsiter.setLastName("Smith");
        register(userToRegsiter);
    }

    public User findUserById(Long id) {
        return findUserIfPresent(id);
    }

    public User getUserById(Long id) {
        return findUserIfPresent(id);
    }

    public User findUserByEmailAddress(String email) {
        return ((userRepository.findByEmailIgnoreCase(email)).orElseThrow(() -> new UserNotFoundException(email)));
    }

    public User findUserToAddExercise(Long id) {
        return getUser(userRepository.findByIdWithExercise(id), id);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User register(User userToRegistering) throws EmailAddressIsTakenException {
        isEmailAvailable(userToRegistering.getEmail());
        userToRegistering.setPassword(passwordEncoder.encode(userToRegistering.getPassword()));
        userToRegistering.setRoles("ROLE_USER");
        return userRepository.save(userToRegistering);
    }

    @Transactional
    public void updateUserPassword(Long id, UpdateUserPasswordRequest updateUserPasswordRequest) {
        User user = findUserIfPresent(id);
        user.setPassword(passwordEncoder.encode(updateUserPasswordRequest.getPassword()));
    }

    public void deleteUserById(Long id) {
        userRepository.delete(findUserIfPresent(id));
    }

    private void isEmailAvailable(String email) throws EmailAddressIsTakenException {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        if (user.isPresent()) {
            throw new EmailAddressIsTakenException(email);
        }
    }

    private User getUser(Optional<User> user, Long id) {
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    private User findUserIfPresent(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByIdWithTags(Long userIdFromJWTToken) {
        return userRepository.findByIdWithTag(userIdFromJWTToken);
    }
}
