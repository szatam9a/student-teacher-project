package learningprojectbackend.studies.model.dto.exercise;

import lombok.Getter;

import java.util.List;

@Getter
public class AnswerDto {
    private Long id;
    private String sentenceBeginning;
    private String sentenceEnding;
    private List<String> correctAnswers;
    private int position;
    private String answer;
    private boolean isCorrect;
    private String leftPair;
    private String rightPair;
}
