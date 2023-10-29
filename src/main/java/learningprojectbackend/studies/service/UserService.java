package learningprojectbackend.studies.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.auth.exception.EmailAddressIsTakenException;
import learningprojectbackend.auth.exception.UserNotFoundException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
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
    private final JwtTokenDetailsService jwtTokenDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        User userToRegister = new User();
        userToRegister.setNickname("admin");
        userToRegister.setPassword("admin");
        userToRegister.setEmail("smithy@admin.com");
        userToRegister.setFirstName("Tom");
        userToRegister.setLastName("Smith");
        register(userToRegister);
    }

    public User getCurrentUser() {
        return getUserById(jwtTokenDetailsService.getUserIdFromJWTToken());
    }

    public Long getCurrentUserId() {
        return jwtTokenDetailsService.getUserIdFromJWTToken();
    }

    public User getUserById(Long id) {
        return findUserIfPresent(id);
    }

    public User getUserByIdWithExercises(Long userIdFromJWTToken) {
        return userRepository.findByIdWithExercises(userIdFromJWTToken);
    }

    public User getUserByIdWithTags(Long userIdFromJWTToken) {
        return userRepository.findByIdWithTags(userIdFromJWTToken);
    }

    public User findUserByEmailAddress(String email) {
        return ((userRepository.findByEmailIgnoreCase(email)).orElseThrow(() -> new UserNotFoundException(email)));
    }

    public User findUserToAddExercise(Long id) {
        return userRepository.findByIdWithExercises(id);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUserPassword(Long id, UpdateUserPasswordRequest updateUserPasswordRequest) {
        User user = findUserIfPresent(id);
        user.setPassword(passwordEncoder.encode(updateUserPasswordRequest.getPassword()));
    }

    public User register(User userToRegistering) throws EmailAddressIsTakenException {
        isEmailAvailable(userToRegistering.getEmail());
        userToRegistering.setPassword(passwordEncoder.encode(userToRegistering.getPassword()));
        userToRegistering.setRoles("ROLE_USER");
        return userRepository.save(userToRegistering);
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

    private User getCurrentUser(Optional<User> user, Long id) {
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    private User findUserIfPresent(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
