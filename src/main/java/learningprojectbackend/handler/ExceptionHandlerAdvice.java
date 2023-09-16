package learningprojectbackend.handler;

import learningprojectbackend.exception.EmailAddressIsTakenException;
import learningprojectbackend.exception.ExerciseNotFoundException;
import learningprojectbackend.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

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

    @ExceptionHandler(ExerciseNotFoundException.class)
    public ProblemDetail handleExerciseNotFoundException(ExerciseNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Exercise not found with id: %d", e.getId()));
        problemDetail.setTitle("Exercise not found");
        problemDetail.setType(URI.create("Exercise-not-found"));
        return problemDetail;
    }
}
