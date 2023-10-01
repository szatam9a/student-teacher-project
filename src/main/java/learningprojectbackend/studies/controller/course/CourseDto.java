package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String title;
    private List<Lesson> lessons = new LinkedList<>();
    private List<Tag> tags = new LinkedList<>();

}
