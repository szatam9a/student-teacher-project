package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.controller.course.CreateLessonRequest;
import learningprojectbackend.studies.controller.course.LessonDto;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.service.CourseService;
import learningprojectbackend.studies.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final ModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDto createLesson(@RequestBody CreateLessonRequest createLessonRequest) {
        return mapper.toLessonDto(lessonService.createLesson(createLessonRequest));
    }
}
