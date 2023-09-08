package learningprojectbackend.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private Long id;
    private String email;

    public UserNotFoundException(String email) {
        this.email = email;
    }

    public UserNotFoundException(Long id) {
        this.id = id;
    }
}
