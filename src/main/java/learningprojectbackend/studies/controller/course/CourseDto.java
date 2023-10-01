package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.controller.tag.TagDto;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String title;
    private List<LessonDto> lessons = new LinkedList<>();
    private List<TagDto> tags = new LinkedList<>();

}
