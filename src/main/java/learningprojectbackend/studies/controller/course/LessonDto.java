package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import lombok.Getter;

import java.util.List;

@Getter
public class LessonDto {
    private Long id;
    private String title;
    private List<Studerial> studerials;
    private List<Tag> tags;
}
