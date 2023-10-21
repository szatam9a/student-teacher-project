package learningprojectbackend.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecaptchaMismatchException extends RuntimeException {
    private final String token;

}
