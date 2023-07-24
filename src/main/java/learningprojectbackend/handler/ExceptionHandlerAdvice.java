package learningprojectbackend.handler;

import learningprojectbackend.expcetion.UserNotFoundException;
import learningprojectbackend.expcetion.UsernameIsTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UsernameIsTakenException.class)
    public ProblemDetail handleUsernameIsTakenException(UsernameIsTakenException e) {
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.CONFLICT,
                        String.format("This username is already in use: %s", e.getUsername()));
        problemDetail.setTitle("Username is already taken");
        problemDetail.setType(URI.create("username-is-taken"));
        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("User not found with id: %d", e.getId()));
        problemDetail.setTitle("User not found");
        problemDetail.setType(URI.create("user-not-found"));
        return problemDetail;
    }


}
