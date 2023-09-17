package learningprojectbackend.studies.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.exercise.CreateExerciseRequest;
import learningprojectbackend.studies.controller.exercise.ExerciseDto;
import learningprojectbackend.studies.exception.ExerciseNotFoundException;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.repository.ExerciseRepository;
import learningprojectbackend.studies.repository.UserRepository;
import learningprojectbackend.studies.service.entity.exercise.Answer;
import learningprojectbackend.studies.service.entity.exercise.Exercise;
import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import learningprojectbackend.studies.service.entity.user.User;
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
        return mapper.toExerciseDto(userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken()).getExerciseList());
    }

    @Transactional
    public ExerciseDto createExercise(CreateExerciseRequest createExerciseRequest) {
        User owner = userService.findUserToAddExercise(jwtTokenDetailsService.getUserIdFromJWTToken());
        Exercise toSave = exerciseRepository.save(mapper.toExercise(createExerciseRequest));
        owner.addExercise(toSave);
        return mapper.toExerciseDto(toSave);
    }

    private Exercise retrieveExercise(Long id) {
        Exercise exerciseToRetrieve = findExerciseById(id);
        if (userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken()).getId().equals(exerciseToRetrieve.getUser().getId())) {
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
