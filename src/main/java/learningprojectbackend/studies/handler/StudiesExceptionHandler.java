package learningprojectbackend.studies.handler;

import learningprojectbackend.studies.exception.AnswerTypeMismatch;
import learningprojectbackend.studies.exception.ExerciseNotFoundException;
import learningprojectbackend.studies.exception.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class StudiesExceptionHandler {

    @ExceptionHandler(TagNotFoundException.class)
    public ProblemDetail handleTagNotFoundException(TagNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Tag not found with id: %d", e.getId())
        );
        problemDetail.setTitle("Tag not found");
        problemDetail.setType(URI.create("tag-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(ExerciseNotFoundException.class)
    public ProblemDetail handleExerciseNotFoundException(ExerciseNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Exercise not found with id: %d", e.getId()));
        problemDetail.setTitle("Exercise not found");
        problemDetail.setType(URI.create("exercise-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(AnswerTypeMismatch.class)
    public ProblemDetail handleAnswerTypeMismatch(AnswerTypeMismatch e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                e.getDetails()
        );
        problemDetail.setTitle("Invalid answer parameters");
        problemDetail.setType(URI.create("invalid-answer-parameters"));
        return problemDetail;
    }
}
