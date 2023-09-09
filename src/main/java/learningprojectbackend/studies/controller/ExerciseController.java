package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.model.dto.exercise.CreateExerciseDto;
import learningprojectbackend.studies.model.dto.exercise.ExerciseDto;
import learningprojectbackend.studies.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseDto> getAllExercise() {
        return exerciseService.getAllExercise();
    }

    @GetMapping("/{id}")
    public ExerciseDto getExerciseById(@PathVariable("id") Long id) {
        return exerciseService.getExerciseById(id);
    }

    @PostMapping
    public ExerciseDto createExercise(@RequestBody CreateExerciseDto createExerciseDto) {
        return exerciseService.createExercise(createExerciseDto);
    }
}
