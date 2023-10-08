package learningprojectbackend.studies.handler;

import learningprojectbackend.studies.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.stream.Collectors;

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

    @ExceptionHandler(TagsNotFoundException.class)
    public ProblemDetail handleTagsNotFoundException(TagsNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Tags not found with id: %s", e.getIds().stream().map(String::valueOf).collect(Collectors.joining(", ")))
        );
        problemDetail.setTitle("Tags not found");
        problemDetail.setType(URI.create("tags-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ProblemDetail handleCourseNotFoundException(CourseNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Course not found with id: %d", e.getId())
        );
        problemDetail.setTitle("Course not found");
        problemDetail.setType(URI.create("course-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(CoursesNotFoundException.class)
    public ProblemDetail handleCoursesNotFoundException(CoursesNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Courses not found with id: %s", e.getIds().stream().map(String::valueOf).collect(Collectors.joining(", ")))
        );
        problemDetail.setTitle("Courses not found");
        problemDetail.setType(URI.create("courses-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(LessonNotFoundException.class)
    public ProblemDetail handleLessonNotFoundException(LessonNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Lesson not found with id: %d", e.getId())
        );
        problemDetail.setTitle("Lesson not found");
        problemDetail.setType(URI.create("lesson-not-found"));
        return problemDetail;
    }

    @ExceptionHandler(LessonsNotFoundException.class)
    public ProblemDetail handleLessonsNotFoundException(LessonsNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                String.format("Lessons not found with id: %s", e.getIds().stream().map(String::valueOf).collect(Collectors.joining(", ")))
        );
        problemDetail.setTitle("Lesson not found");
        problemDetail.setType(URI.create("lessons-not-found"));
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
}
