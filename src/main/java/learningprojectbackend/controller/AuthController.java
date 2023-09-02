package learningprojectbackend.controller;

import learningprojectbackend.model.dto.LoginRequest;
import learningprojectbackend.service.auth.AuthService;
import learningprojectbackend.service.auth.TokenService;
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

    private final TokenService tokenService;
    private final AuthService authService;
    @PreAuthorize("permitAll()")
    @PostMapping("/sign-in")
    public String loginRequest(@RequestBody LoginRequest loginRequest){
            return tokenService.generateToken(authService.authenticateLoginRequest(loginRequest));
    }
}
