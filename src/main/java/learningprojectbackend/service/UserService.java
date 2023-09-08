package learningprojectbackend.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.exception.UserNotFoundException;
import learningprojectbackend.exception.UsernameIsTakenException;
import learningprojectbackend.model.ModelMapper;
import learningprojectbackend.model.dto.user.CreateUserDto;
import learningprojectbackend.model.dto.user.UpdateUserPasswordDto;
import learningprojectbackend.model.dto.user.UpdateUsernameDto;
import learningprojectbackend.model.dto.user.UserDto;
import learningprojectbackend.model.entity.user.User;
import learningprojectbackend.repository.UserRepository;
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
        registering(new CreateUserDto("admin", "admin", "smithy@admin.com", "Smith", "Tom"));
    }

    public UserDto findUserById(Long id) {
        return mapper.toUserDto(findUserIfPresent(id));
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

    public UserDto registering(CreateUserDto createUserDto) throws UsernameIsTakenException {
        isUsernameAvailable(createUserDto.getUsername());
        User userToRegistering = mapper.toUser(createUserDto);
        userToRegistering.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userToRegistering.setRoles("ROLE_USER");
        return mapper.toUserDto(userRepository.save(userToRegistering));
    }

    @Transactional
    public void updateUserPassword(Long id, UpdateUserPasswordDto updateUserPasswordDto) {
        User user = findUserIfPresent(id);
        user.setPassword(passwordEncoder.encode(updateUserPasswordDto.getPassword()));
    }

    @Transactional
    public void updateUsername(Long id, UpdateUsernameDto updateUsernameDto) {
        User user = findUserIfPresent(id);
        isUsernameAvailable(updateUsernameDto.getUsername());
        user.setUsername(updateUsernameDto.getUsername());
    }

    public void deleteUserById(Long id) {
        userRepository.delete(findUserIfPresent(id));
    }

    private void isUsernameAvailable(String username) throws UsernameIsTakenException {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);
        if (user.isPresent()) {
            throw new UsernameIsTakenException(username);
        }
    }

    private User getUser(Optional<User> user, Long id) {
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    private User findUserIfPresent(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
