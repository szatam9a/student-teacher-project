package learningprojectbackend.service;

import learningprojectbackend.model.ExerciseType;
import learningprojectbackend.model.entity.exercize.Exercise;
import learningprojectbackend.model.entity.exercize.MatchPairExerciseAnswer;
import learningprojectbackend.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        Exercise exercise = new Exercise();
        exercise.setExerciseType(ExerciseType.match);
        exercise.setTitle("match");
        MatchPairExerciseAnswer matchPairExerciseAnswer = new MatchPairExerciseAnswer();
        matchPairExerciseAnswer.setLeftPair("joke");
        matchPairExerciseAnswer.setRightPair("ekoj");
        exercise.addAnswer(matchPairExerciseAnswer);
        exerciseRepository.save(exercise);
    }
}