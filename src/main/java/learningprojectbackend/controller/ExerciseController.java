package learningprojectbackend.controller;

import learningprojectbackend.model.dto.exercise.ExerciseDto;
import learningprojectbackend.model.entity.exercise.Exercise;
import learningprojectbackend.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseDto> getAllExercise(){
        return exerciseService.getAllExercise();
    }
    @GetMapping("/{id}")
    public ExerciseDto getExerciseById(@Param("id") long id){
        return exerciseService.getExerciseById(id);
    }
}
