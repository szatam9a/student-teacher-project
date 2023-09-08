package learningprojectbackend.service.auth;

import learningprojectbackend.model.dto.LoginRequest;
import learningprojectbackend.model.dto.user.UserToken;
import learningprojectbackend.service.UserService;
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
        userToken.setUserDto(userService.findUserByEmailAddress(loginRequest.getUsername()));
        return userToken;
    }

    public Authentication authenticateLoginRequest(LoginRequest loginRequest) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
