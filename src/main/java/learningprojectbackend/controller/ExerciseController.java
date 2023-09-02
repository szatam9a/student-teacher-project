package learningprojectbackend.controller;

import learningprojectbackend.model.dto.exercise.CreateExerciseDto;
import learningprojectbackend.model.dto.exercise.ExerciseDto;
import learningprojectbackend.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ExerciseDto getExerciseById(@PathVariable("id") Long id, Authentication authentication, Principal principal) {
        System.out.println(authentication.getDetails());
        System.out.println(principal.getName());
        return exerciseService.getExerciseById(id);
    }

    @PostMapping
    public ExerciseDto createExercise(@RequestBody CreateExerciseDto createExerciseDto) {
        return exerciseService.createExercise(createExerciseDto);
    }
}
