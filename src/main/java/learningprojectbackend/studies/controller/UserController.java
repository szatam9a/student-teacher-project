package learningprojectbackend.studies.controller;

import jakarta.validation.Valid;

import learningprojectbackend.exception.UsernameIsTakenException;
import learningprojectbackend.studies.model.dto.user.CreateUserDto;
import learningprojectbackend.studies.model.dto.user.UpdateUserPasswordDto;
import learningprojectbackend.studies.model.dto.user.UpdateUsernameDto;
import learningprojectbackend.studies.model.dto.user.UserDto;
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

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findUsersAllData(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAllUser() {
        return userService.findAllUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createNewUser(@RequestBody @Valid CreateUserDto createUserDto) throws UsernameIsTakenException {
        return userService.registering(createUserDto);
    }

    @PatchMapping("/{userId}/username")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public void updateUsername(@RequestBody @Valid UpdateUsernameDto updateUsernameDto, @PathVariable Long userId) {
        userService.updateUsername(userId, updateUsernameDto);
    }

    @PatchMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public void updateUserPassword(@RequestBody @Valid UpdateUserPasswordDto updateUserPasswordDto, @PathVariable Long userId) {
        userService.updateUserPassword(userId, updateUserPasswordDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
