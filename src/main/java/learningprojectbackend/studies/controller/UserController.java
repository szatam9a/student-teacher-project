package learningprojectbackend.studies.controller;

import jakarta.validation.Valid;
import learningprojectbackend.auth.exception.EmailAddressIsTakenException;
import learningprojectbackend.studies.controller.user.RegistrationRequest;
import learningprojectbackend.studies.controller.user.UpdateUserPasswordRequest;
import learningprojectbackend.studies.controller.user.UserDto;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findUsersAllData(@PathVariable Long userId) {
        return mapper.toUserDto(userService.getUserById(userId));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAllUser() {
        return mapper.toUserDto(userService.findAllUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createNewUser(@RequestBody @Valid RegistrationRequest registrationRequest) throws EmailAddressIsTakenException {
        return mapper.toUserDto(userService.register(mapper.toUser(registrationRequest)));
    }

    @PatchMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUserPassword(@RequestBody @Valid UpdateUserPasswordRequest updateUserPasswordRequest, @PathVariable Long userId) {
        userService.updateUserPassword(userId, updateUserPasswordRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
