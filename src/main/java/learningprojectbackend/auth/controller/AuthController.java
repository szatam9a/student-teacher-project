package learningprojectbackend.auth.controller;

import learningprojectbackend.auth.model.dto.LoginRequest;
import learningprojectbackend.auth.service.AuthService;
import learningprojectbackend.studies.controller.user.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public UserToken loginUser(@RequestBody LoginRequest loginRequest) {
        return authService.getTokenAndUser(loginRequest);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/logout")
    public void logoutUser() {

    }
}
