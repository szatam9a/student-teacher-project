package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.controller.exercise.*;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseDto> getAllExercise() {
        return mapper.toExerciseDto(exerciseService.getAllExercise());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDto createExercise(@RequestBody CreateExerciseRequest createExerciseRequest) {
        return mapper.toExerciseDto(exerciseService.createExercise(createExerciseRequest));
    }

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<ExerciseFilterDto> getAllFiltered(@RequestBody PaginationRequest paginationRequest) {
        return exerciseService.getAllFiltered(paginationRequest).map(mapper::toExerciseFilterDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExercise(@PathVariable Long id, @RequestBody UpdateExerciseRequest updateExerciseRequest) {
        exerciseService.updateExercise(id, updateExerciseRequest);
    }

}
