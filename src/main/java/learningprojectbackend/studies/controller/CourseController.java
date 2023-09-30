package learningprojectbackend.studies.controller;

import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.service.CourseService;
import learningprojectbackend.studies.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final ModelMapper mapper;

}
