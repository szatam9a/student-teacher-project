package learningprojectbackend.studies.service;

import jakarta.annotation.PostConstruct;
import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.exercise.CreateExerciseRequest;
import learningprojectbackend.studies.exception.AnswerTypeMismatch;
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
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @PostConstruct
    private void makeDefaultExercise() {
        User user = new User();
        user.setExerciseList(new LinkedList<>());
        Exercise exercise = new Exercise();
        user.addExercise(exercise);
        userRepository.save(user);
        exercise.setExerciseType(ExerciseType.MATCH);
        exercise.setTitle("match");
        Answer matchPairExerciseAnswer = new Answer();
        matchPairExerciseAnswer.setLeftPair("joke");
        matchPairExerciseAnswer.setRightPair("ekoj");
        exercise.addAnswer(matchPairExerciseAnswer);
        exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(Long id) {
        return retrieveExercise(id);
    }

    public List<Exercise> getAllExercise() {
        return userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken()).getExerciseList();
    }

    @Transactional
    public Exercise createExercise(CreateExerciseRequest createExerciseRequest) {
        validateAnswersByType(createExerciseRequest.getAnswers(), createExerciseRequest.getExerciseType());
        User owner = userService.findUserToAddExercise(jwtTokenDetailsService.getUserIdFromJWTToken());
        Exercise toSave = exerciseRepository.save(mapper.toExercise(createExerciseRequest));
        owner.addExercise(toSave);
        return toSave;
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

    private void validateAnswersByType(List<Answer> answers, ExerciseType exerciseType) {
        switch (exerciseType.getValue()) {
            case "MATCH" -> validateMatch(answers);
            case "FIND" -> validateFind(answers);
            case "ORDER" -> validateOrder(answers);
        }
    }

    private void validateMatch(List<Answer> answers) {
        for (Answer answer : answers) {
            boolean condition = answer.getLeftPair() == null ||
                    answer.getLeftPair().isBlank() ||
                    answer.getRightPair() == null ||
                    answer.getRightPair().isBlank();
            if (condition) {
                throw new AnswerTypeMismatch("details todo");
            }
        }
    }

    private void validateFind(List<Answer> answers) {
        for (Answer answer : answers) {
            boolean condition = answer.getAnswer() == null ||
                    answer.getAnswer().isBlank();
            if (condition) {
                throw new AnswerTypeMismatch("details todo");
            }
        }
    }

    private void validateOrder(List<Answer> answers) {
        for (Answer answer : answers) {
            boolean condition = answer.getAnswer() == null ||
                    answer.getAnswer().isBlank();
            if (condition) {
                throw new AnswerTypeMismatch("details todo");
            }
        }
    }
}