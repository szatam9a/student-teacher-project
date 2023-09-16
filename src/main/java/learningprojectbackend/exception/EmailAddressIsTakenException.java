package learningprojectbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailAddressIsTakenException extends RuntimeException {
    private final String emailAddress;
}
