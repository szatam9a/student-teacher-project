package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.controller.exercise.CreateExerciseRequest;
import learningprojectbackend.studies.controller.exercise.ExerciseDto;
import learningprojectbackend.studies.controller.exercise.PaginationRequest;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        return mapper.toExerciseDto(exerciseService.getAllExercise());
    }

    @PostMapping("/filter")
    public Page<ExerciseDto> getAllFiltered(@RequestBody PaginationRequest paginationRequest) {
        return mapper.toExerciseDto(exerciseService.getAllFiltered(paginationRequest));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDto createExercise(@RequestBody CreateExerciseRequest createExerciseRequest) {
        return mapper.toExerciseDto(exerciseService.createExercise(createExerciseRequest));
    }
}
