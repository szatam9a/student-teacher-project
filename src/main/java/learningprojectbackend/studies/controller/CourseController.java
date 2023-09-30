package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.controller.course.CourseDto;
import learningprojectbackend.studies.controller.course.CreateCourseRequest;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.service.CourseService;
import learningprojectbackend.studies.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final ModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourser(@RequestBody CreateCourseRequest createCourseRequest) {
        return mapper.toCourseDto(courseService.createCourse(createCourseRequest));
    }
}
