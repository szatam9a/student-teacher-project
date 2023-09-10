package learningprojectbackend.auth.service;

import learningprojectbackend.auth.model.dto.LoginRequest;
import learningprojectbackend.studies.model.dto.user.UserToken;
import learningprojectbackend.studies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    public UserToken getTokenAndUser(LoginRequest loginRequest) {
        UserToken userToken = new UserToken();
        userToken.setToken(tokenService.generateToken(authenticateLoginRequest(loginRequest)));
        userToken.setUser(userService.findUserByEmailAddress(loginRequest.getEmailAddress()));
        return userToken;
    }

    public Authentication authenticateLoginRequest(LoginRequest loginRequest) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
