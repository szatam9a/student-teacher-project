package learningprojectbackend.handler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleInvalidMethodArgumentInRequest(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                e.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","))
        );
        problemDetail.setTitle("Invalid request parameters");
        problemDetail.setType(URI.create("invalid-request-parameters"));
        return problemDetail;
    }
}
