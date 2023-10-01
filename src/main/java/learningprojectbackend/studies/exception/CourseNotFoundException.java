package learningprojectbackend.studies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CourseNotFoundException extends RuntimeException {
    private final Long id;
}
