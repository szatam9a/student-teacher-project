package learningprojectbackend.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class AuthExceptionHandler {
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
