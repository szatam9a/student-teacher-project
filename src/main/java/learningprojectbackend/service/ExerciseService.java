package learningprojectbackend.service;

import learningprojectbackend.exception.ExerciseNotFoundException;
import learningprojectbackend.model.ModelMapper;
import learningprojectbackend.model.dto.exercise.CreateExerciseDto;
import learningprojectbackend.model.dto.exercise.ExerciseDto;
import learningprojectbackend.model.entity.exercise.Answer.ExerciseType;
import learningprojectbackend.model.entity.exercise.Answer.MatchPairExerciseAnswer;
import learningprojectbackend.model.entity.exercise.Exercise;
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

    public ExerciseDto getExerciseById(Long id) {
        ;
        return mapper.toExerciseDto(exerciseRepository.findById(id).orElseThrow(
                () -> new ExerciseNotFoundException(id)));
    }

    public List<ExerciseDto> getAllExercise() {
        return mapper.toExerciseDto(
                exerciseRepository.findAll());
    }

    public ExerciseDto createExercise(CreateExerciseDto createExerciseDto) {
        return mapper.toExerciseDto(
                exerciseRepository.save(
                        mapper.toExercise(createExerciseDto)));
    }
}
