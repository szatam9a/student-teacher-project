package learningprojectbackend.studies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class LessonsNotFoundException extends RuntimeException {
    private final List<Long> ids;
}
