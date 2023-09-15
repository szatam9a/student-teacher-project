package learningprojectbackend.studies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagNotFoundException extends RuntimeException {
    private final Long id;
}
