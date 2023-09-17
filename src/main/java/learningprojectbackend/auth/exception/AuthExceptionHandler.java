package learningprojectbackend.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(EmailAddressIsTakenException.class)
    public ProblemDetail handleUsernameIsTakenException(EmailAddressIsTakenException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                String.format("This username is already in use: %s", e.getEmailAddress()));
        problemDetail.setTitle("Username is already taken");
        problemDetail.setType(URI.create("username-is-taken"));
        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("User not found with id: %d or email: %s", e.getId(), e.getEmail()));
        problemDetail.setTitle("User not found");
        problemDetail.setType(URI.create("user-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                String.format(e.getMessage()));
        problemDetail.setTitle("Bad credentials");
        problemDetail.setType(URI.create("bad-credentials"));
        return problemDetail;
    }

    @ExceptionHandler(NoAuthorizationToAccessResourcesException.class)
    public ProblemDetail handleNoAuthorizationToAccessResource(NoAuthorizationToAccessResourcesException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                String.format(e.getMessage()));
        problemDetail.setTitle("no authority");
        problemDetail.setType(URI.create("no-authority"));
        return problemDetail;
    }
}
