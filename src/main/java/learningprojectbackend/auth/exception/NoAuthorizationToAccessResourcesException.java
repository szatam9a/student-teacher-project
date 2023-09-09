package learningprojectbackend.auth.exception;

public class NoAuthorizationToAccessResourcesException extends RuntimeException {
    public NoAuthorizationToAccessResourcesException(String message) {
        super(message);
    }
}
