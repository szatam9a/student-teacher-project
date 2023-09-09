package learningprojectbackend.studies.controller;

import jakarta.validation.Valid;
import learningprojectbackend.studies.model.dto.user.CreateUserDto;
import learningprojectbackend.studies.model.dto.user.UserDto;
import learningprojectbackend.studies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registeringNewUser(@RequestBody @Valid CreateUserDto userRegistrationDto) {
        return this.userService.registering(userRegistrationDto);
    }
}
