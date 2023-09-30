package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.tag.Tag;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseDto {
    private Long id;
    private String title;
    private List<Lesson> lessons;
    private List<Tag> tags;
}
