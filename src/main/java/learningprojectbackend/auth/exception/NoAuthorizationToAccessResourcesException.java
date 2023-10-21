package learningprojectbackend.auth.exception;

import lombok.Getter;

@Getter
public class NoAuthorizationToAccessResourcesException extends RuntimeException {
    public NoAuthorizationToAccessResourcesException(String message) {
        super(message);
    }
}
