package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.controller.studerial.StuderialDto;
import learningprojectbackend.studies.controller.tag.TagDto;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class LessonDto {
    private Long id;
    private String title;
    private List<StuderialDto> studerials = new LinkedList<>();
    private List<TagDto> tags = new LinkedList<>();
}
