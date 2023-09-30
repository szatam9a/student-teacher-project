package learningprojectbackend.auth.service;

import learningprojectbackend.auth.model.dto.LoginRequest;
import learningprojectbackend.studies.controller.user.UserToken;
import learningprojectbackend.studies.model.ModelMapper;
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
    private final ModelMapper mapper;

    public UserToken getTokenAndUser(LoginRequest loginRequest) {
        UserToken userToken = new UserToken();
        userToken.setToken(tokenService.generateToken(authenticateLoginRequest(loginRequest)));
        userToken.setUser(mapper.toUserDto(userService.findUserByEmailAddress(loginRequest.getEmail())));
        return userToken;
    }

    public Authentication authenticateLoginRequest(LoginRequest loginRequest) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
