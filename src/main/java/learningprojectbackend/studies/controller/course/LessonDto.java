package learningprojectbackend.studies.controller.course;

import learningprojectbackend.studies.controller.studerial.StuderialDto;
import learningprojectbackend.studies.controller.tag.TagDto;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class LessonDto {
    private Long id;
    private String title;
    private Set<StuderialDto> studerials = new HashSet<>();
    private Set<TagDto> tags = new HashSet<>();
}
