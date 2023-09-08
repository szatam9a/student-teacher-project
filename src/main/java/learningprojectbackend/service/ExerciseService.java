package learningprojectbackend.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.config.jwt.JwtTokenDetailsService;
import learningprojectbackend.exception.ExerciseNotFoundException;
import learningprojectbackend.model.ModelMapper;
import learningprojectbackend.model.dto.exercise.CreateExerciseDto;
import learningprojectbackend.model.dto.exercise.ExerciseDto;
import learningprojectbackend.model.entity.exercise.Answer.Answer;
import learningprojectbackend.model.entity.exercise.Answer.ExerciseType;
import learningprojectbackend.model.entity.exercise.Exercise;
import learningprojectbackend.model.entity.user.User;
import learningprojectbackend.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserService userService;
    private final JwtTokenDetailsService jwtTokenDetailsService;
    private final ModelMapper mapper;

    public ExerciseDto getExerciseById(Long id) {
        return mapper.toExerciseDto(findExerciseById(id));
    }

    public List<ExerciseDto> getAllExercise() {
        return mapper.toExerciseDto(
                exerciseRepository.findAll());
    }

    @Transactional
    public ExerciseDto createExercise(CreateExerciseDto createExerciseDto) {
        User owner = userService.findUserToAddExercise(jwtTokenDetailsService.getUserIdFromJWTToken());
        Exercise toSave = exerciseRepository.save(
                mapper.toExercise(createExerciseDto));
        owner.addExercise(toSave);
        return mapper.toExerciseDto(toSave);
    }

    private Exercise isTheUserTheOwner(Long id) {
        return findExerciseById(id);
    }

    private Exercise findExerciseById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(
                () -> new ExerciseNotFoundException(id));
    }

    @PostConstruct
    private void makeDefaultExercise() {
        Exercise exercise = new Exercise();
        exercise.setExerciseType(ExerciseType.match);
        exercise.setTitle("match");
        Answer matchPairExerciseAnswer = new Answer();
        matchPairExerciseAnswer.setLeftPair("joke");
        matchPairExerciseAnswer.setRightPair("ekoj");
        exercise.addAnswer(matchPairExerciseAnswer);
        exerciseRepository.save(exercise);
    }
}
