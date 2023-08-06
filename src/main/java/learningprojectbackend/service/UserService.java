package learningprojectbackend.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.exception.UserNotFoundException;
import learningprojectbackend.exception.UsernameIsTakenException;
import learningprojectbackend.model.ModelMapper;
import learningprojectbackend.repository.UserRepository;
import learningprojectbackend.model.entity.User;
import learningprojectbackend.model.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserDto findUserById(Long id) {
        return mapper.toUserDto(findUserIfPresent(id));
    }
    @PostConstruct
    void init(){
        registering(new CreateUserDto("admin","admin","smithy@admin.com","Smith","Tom"));
    }
    public UserDto registering(CreateUserDto createUserDto) throws UsernameIsTakenException {
        isUsernameAvailable(createUserDto.getUsername());
        User userToRegistering = mapper.toUser(createUserDto);
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

    private User findUserIfPresent(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
