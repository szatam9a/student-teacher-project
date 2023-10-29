package learningprojectbackend.studies.controller.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    private Long id;
    private int position;
    private String answer;
    @JsonProperty("isCorrect")
    private boolean correct;
    private String leftPair;
    private String rightPair;
}
