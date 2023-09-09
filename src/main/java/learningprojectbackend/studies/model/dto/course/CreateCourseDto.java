package learningprojectbackend.studies.model.dto.course;

import learningprojectbackend.studies.model.entity.course.Lesson;
import learningprojectbackend.studies.model.entity.tag.Tag;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateCourseDto {
    private String title;
    private List<Lesson> lessonList;
    private List<Tag> tagList;
}
