package learningprojectbackend.studies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExerciseNotFoundException extends RuntimeException {
    private final Long id;
}
