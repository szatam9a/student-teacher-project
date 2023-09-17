package learningprojectbackend.studies.controller;

import jakarta.validation.Valid;
import learningprojectbackend.studies.controller.user.RegistrationRequest;
import learningprojectbackend.studies.controller.user.UserDto;
import learningprojectbackend.studies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registeringNewUser(@RequestBody @Valid RegistrationRequest userRegistrationDto) {
        return this.userService.register(userRegistrationDto);
    }
}
