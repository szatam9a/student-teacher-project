package learningprojectbackend.studies.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.Predicate;
import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.exercise.CreateExerciseRequest;
import learningprojectbackend.studies.controller.exercise.PaginationRequest;
import learningprojectbackend.studies.controller.exercise.UpdateExerciseRequest;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        return userService.getUserByIdWithExercises(userService.getCurrentUserId()).getExerciseList();
    }

    public Page<Exercise> getAllFiltered(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
        Specification<Exercise> searchSpecification = (root, query, criteriaBuilder) -> {

            Predicate paginationFilter = criteriaBuilder.like(root.get(paginationRequest.getFilter().getFilterOn()), "%" + paginationRequest.getFilter().getFilter() + "%");
            Predicate userIdFilter = criteriaBuilder.equal(root.get("user").get("id"), userService.getCurrentUserId());
            return criteriaBuilder.and(paginationFilter, userIdFilter);
        };
        return exerciseRepository.findAll(searchSpecification, pageable);
    }

    @Transactional
    public Exercise createExercise(CreateExerciseRequest createExerciseRequest) {
        validateAnswersByType(createExerciseRequest.getAnswers(), createExerciseRequest.getExerciseType());
        User owner = userService.findUserToAddExercise(jwtTokenDetailsService.getUserIdFromJWTToken());
        Exercise toSave = exerciseRepository.save(mapper.toExercise(createExerciseRequest));
        owner.addExercise(toSave);
        toSave.setOwner();
        return toSave;
    }

    @Transactional
    public void updateExercise(Long id, UpdateExerciseRequest updateExerciseRequest) {
        Exercise exerciseToUpdate = getExerciseById(id);
        mapper.toExercise(exerciseToUpdate, updateExerciseRequest);
        exerciseToUpdate.setOwner();
        exerciseRepository.save(exerciseToUpdate);
    }


    private Exercise retrieveExercise(Long id) {
        Exercise exerciseToRetrieve = findExerciseById(id);
        checkOwnership(exerciseToRetrieve);
        return exerciseToRetrieve;
    }

    private Exercise findExerciseById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new ExerciseNotFoundException(id));
    }

    private void checkOwnership(Exercise exercise) {
        boolean isUserAuthorizedToAccessCourse = userService.getCurrentUserId().equals(exercise.getUser().getId());
        if (!isUserAuthorizedToAccessCourse) {
            throw new NoAuthorizationToAccessResourcesException("No Authorization to retrieve course with id: " + exercise.getId());
        }
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
            boolean condition = answer.getLeftPair() == null || answer.getLeftPair().isBlank() || answer.getRightPair() == null || answer.getRightPair().isBlank();
            if (condition) {
                throw new AnswerTypeMismatch("details todo");
            }
        }
    }

    private void validateFind(List<Answer> answers) {
        for (Answer answer : answers) {
            boolean condition = answer.getAnswer() == null || answer.getAnswer().isBlank();
            if (condition) {
                throw new AnswerTypeMismatch("details todo");
            }
        }
    }

    private void validateOrder(List<Answer> answers) {
        for (Answer answer : answers) {
            boolean condition = answer.getAnswer() == null || answer.getAnswer().isBlank();
            if (condition) {
                throw new AnswerTypeMismatch("details todo");
            }
        }
    }
}