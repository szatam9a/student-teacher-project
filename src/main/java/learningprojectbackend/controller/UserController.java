package learningprojectbackend.controller;

import jakarta.validation.Valid;

import learningprojectbackend.expcetion.UsernameIsTakenException;
import learningprojectbackend.model.dto.user.CreateUserDto;
import learningprojectbackend.model.dto.user.UpdateUserPasswordDto;
import learningprojectbackend.model.dto.user.UpdateUsernameDto;
import learningprojectbackend.model.dto.user.UserDto;
import learningprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findUsersAllData(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createNewUser(@RequestBody @Valid CreateUserDto createUserDto) throws UsernameIsTakenException {
        return userService.saveNewUser(createUserDto);
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
