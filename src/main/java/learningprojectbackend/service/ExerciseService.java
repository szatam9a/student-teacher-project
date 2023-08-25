package learningprojectbackend.service;

import learningprojectbackend.exception.ExerciseNotFoundException;
import learningprojectbackend.model.ExerciseType;
import learningprojectbackend.model.ModelMapper;
import learningprojectbackend.model.dto.exercise.ExerciseDto;
import learningprojectbackend.model.entity.exercise.Exercise;
import learningprojectbackend.model.entity.exercise.MatchPairExerciseAnswer;
import learningprojectbackend.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper mapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ModelMapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
        Exercise exercise = new Exercise();
        exercise.setExerciseType(ExerciseType.match);
        exercise.setTitle("match");
        MatchPairExerciseAnswer matchPairExerciseAnswer = new MatchPairExerciseAnswer();
        matchPairExerciseAnswer.setLeftPair("joke");
        matchPairExerciseAnswer.setRightPair("ekoj");
        exercise.addAnswer(matchPairExerciseAnswer);
        exerciseRepository.save(exercise);
    }

    public ExerciseDto getExerciseById(long id) {
        return mapper.toExerciseDto(exerciseRepository.findById(id).orElseThrow(
                () -> new ExerciseNotFoundException(id)));
    }

    public List<ExerciseDto> getAllExercise() {
        return mapper.toExerciseDto(exerciseRepository.findAll());
    }
}
