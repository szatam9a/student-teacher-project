package learningprojectbackend.studies.model.entity.user;

import jakarta.persistence.Column;

public class UserDetails {

    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
