package learningprojectbackend.auth.controller;

import learningprojectbackend.auth.model.dto.LoginRequest;
import learningprojectbackend.studies.model.dto.user.UserToken;
import learningprojectbackend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/sign-in")
    public UserToken loginRequest(@RequestBody LoginRequest loginRequest) {
        return authService.getTokenAndUser(loginRequest);
    }
}
