package learningprojectbackend.auth.controller;

import learningprojectbackend.auth.model.dto.LoginRequest;
import learningprojectbackend.auth.service.AuthService;
import learningprojectbackend.studies.controller.user.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public UserToken loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.getTokenAndUser(loginRequest);
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logoutUser() {

    }
}
