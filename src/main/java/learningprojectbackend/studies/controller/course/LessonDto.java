package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class LessonDto {
    private Long id;
    private String title;
    private List<Studerial> studerials = new LinkedList<>();
    private List<Tag> tags = new LinkedList<>();
}
