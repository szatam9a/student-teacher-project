package learningprojectbackend.studies.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TagsNotFoundException extends RuntimeException {
    private final List<Long> ids;
}
