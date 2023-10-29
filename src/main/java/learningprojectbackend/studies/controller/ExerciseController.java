package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.controller.exercise.CreateExerciseRequest;
import learningprojectbackend.studies.controller.exercise.ExerciseDto;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExerciseDto getExerciseById(@PathVariable("id") Long id) {
        return mapper.toExerciseDto(exerciseService.getExerciseById(id));
    }

    @GetMapping
    public List<ExerciseDto> getAllExercise() {
        return null; // TODO: 29/10/2023
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDto createExercise(@RequestBody CreateExerciseRequest createExerciseRequest) {
        return mapper.toExerciseDto(exerciseService.createExercise(createExerciseRequest));
    }
}
