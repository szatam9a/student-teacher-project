package learningprojectbackend.controller;

import learningprojectbackend.model.dto.user.CreateUserDto;
import learningprojectbackend.model.dto.user.UserDto;
import learningprojectbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registeringNewUser(@RequestBody CreateUserDto userRegistrationDto) {
        return this.userService.registering(userRegistrationDto);
    }
}
