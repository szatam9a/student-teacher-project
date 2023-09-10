package learningprojectbackend.studies.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.exception.ExerciseNotFoundException;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.model.dto.exercise.CreateExerciseDto;
import learningprojectbackend.studies.model.dto.exercise.ExerciseDto;
import learningprojectbackend.studies.model.entity.exercise.Answer;
import learningprojectbackend.studies.model.entity.exercise.Exercise;
import learningprojectbackend.studies.model.entity.exercise.ExerciseType;
import learningprojectbackend.studies.model.entity.user.User;
import learningprojectbackend.studies.repository.ExerciseRepository;
import learningprojectbackend.studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final UserService userService;
    private final ExerciseRepository exerciseRepository;
    private final JwtTokenDetailsService jwtTokenDetailsService;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public ExerciseDto getExerciseById(Long id) {
        return mapper.toExerciseDto(retrieveExercise(id));
    }

    public List<ExerciseDto> getAllExercise() {
//        return mapper.toExerciseDto(exerciseRepository.findAllByUserId(jwtTokenDetailsService.getUserIdFromJWTToken()));
        return mapper.toExerciseDto(userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken()).getExerciseList());
    }

    @Transactional
    public ExerciseDto createExercise(CreateExerciseDto createExerciseDto) {
        User owner = userService.findUserToAddExercise(jwtTokenDetailsService.getUserIdFromJWTToken());
        Exercise toSave = exerciseRepository.save(mapper.toExercise(createExerciseDto));
        owner.addExercise(toSave);
        return mapper.toExerciseDto(toSave);
    }

    private Exercise retrieveExercise(Long id) {
        Exercise exerciseToRetrieve = findExerciseById(id);
        if (userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken()).getId() == exerciseToRetrieve.getUser().getId()) {
            return exerciseToRetrieve;
        } else {
            throw new NoAuthorizationToAccessResourcesException("No Authorization to retrieve exercise with id: " + exerciseToRetrieve.getId());
        }
    }

    private Exercise findExerciseById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ExerciseNotFoundException(id));
    }

    @PostConstruct
    private void makeDefaultExercise() {
        User user = new User();
        user.setExerciseList(new LinkedList<>());
        Exercise exercise = new Exercise();
        user.addExercise(exercise);
        userRepository.save(user);
        exercise.setExerciseType(ExerciseType.match);
        exercise.setTitle("match");
        Answer matchPairExerciseAnswer = new Answer();
        matchPairExerciseAnswer.setLeftPair("joke");
        matchPairExerciseAnswer.setRightPair("ekoj");
        exercise.addAnswer(matchPairExerciseAnswer);
        exerciseRepository.save(exercise);
    }
}
