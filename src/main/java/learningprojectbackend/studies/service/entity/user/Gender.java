package learningprojectbackend.studies.service.entity.user;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE"), OTHER("OTHER");
    private String value;

    public String getValue() {
        return value;
    }

    Gender(String value) {
        this.value = value;
    }
}
