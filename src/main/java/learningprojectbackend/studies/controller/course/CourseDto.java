package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.controller.tag.TagDto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String title;
    private Set<LessonDto> lessons = new HashSet<>();
    private Set<TagDto> tags = new HashSet<>();

}
