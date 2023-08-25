package learningprojectbackend.model;

public enum ExerciseType {
    find("FIND"),
    fill("FILL"),
    match("MATCH"),
    order ("ORDER");
    private String value;

    ExerciseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}