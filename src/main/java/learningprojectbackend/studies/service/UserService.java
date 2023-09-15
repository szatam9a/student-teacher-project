package learningprojectbackend.studies.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.exception.UserNotFoundException;
import learningprojectbackend.exception.UsernameIsTakenException;
import learningprojectbackend.studies.controller.dto.user.RegistrationRequest;
import learningprojectbackend.studies.controller.dto.user.UpdateUserPasswordDto;
import learningprojectbackend.studies.controller.dto.user.UserDto;
import learningprojectbackend.studies.model.ModelMapper;
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
    private final ModelMapper mapper;

    @PostConstruct
    void init() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setNickname("admin");
        registrationRequest.setPassword("admin");
        registrationRequest.setEmail("smithy@admin.com");
        registrationRequest.setFirstName("Tom");
        registrationRequest.setLastName("Smith");
        registering(registrationRequest);
    }

    public UserDto findUserById(Long id) {
        return mapper.toUserDto(findUserIfPresent(id));
    }

    public User getUserById(Long id) {
        return (findUserIfPresent(id));
    }

    public UserDto findUserByEmailAddress(String email) {
        return mapper.toUserDto((userRepository.findByEmailIgnoreCase(email)).orElseThrow(() -> new UserNotFoundException(email)));
    }

    public User findUserToAddExercise(Long id) {
        return getUser(userRepository.findByIdWithExercise(id), id);
    }

    public List<UserDto> findAllUser() {
        return mapper.toUserDto(userRepository.findAll());
    }

    public UserDto registering(RegistrationRequest registrationRequest) throws UsernameIsTakenException {
        isEmailAvailable(registrationRequest.getNickname());
        User userToRegistering = mapper.toUser(registrationRequest);
        userToRegistering.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        userToRegistering.setRoles("ROLE_USER");
        return mapper.toUserDto(userRepository.save(userToRegistering));
    }

    @Transactional
    public void updateUserPassword(Long id, UpdateUserPasswordDto updateUserPasswordDto) {
        User user = findUserIfPresent(id);
        user.setPassword(passwordEncoder.encode(updateUserPasswordDto.getPassword()));
    }

    public void deleteUserById(Long id) {
        userRepository.delete(findUserIfPresent(id));
    }

    private void isEmailAvailable(String email) throws UsernameIsTakenException {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        if (user.isPresent()) {
            throw new UsernameIsTakenException(email);
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
