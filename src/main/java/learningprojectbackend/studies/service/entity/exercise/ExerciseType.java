package learningprojectbackend.studies.service.entity.exercise;

public enum ExerciseType {
    FIND("FIND"),
    FILL("FILL"),
    MATCH("MATCH"),
    ORDER("ORDER");
    private String value;

    public String getValue() {
        return value;
    }

    ExerciseType(String value) {
        this.value = value;
    }
}
