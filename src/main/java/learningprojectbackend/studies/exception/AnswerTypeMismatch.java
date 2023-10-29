package learningprojectbackend.studies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AnswerTypeMismatch extends RuntimeException {
    private final String details;
}
