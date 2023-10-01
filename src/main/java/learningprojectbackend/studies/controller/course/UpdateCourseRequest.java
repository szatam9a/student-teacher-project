package learningprojectbackend.studies.controller.course;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateCourseRequest {
    private Long id;
    private List<Long> lessonIds;
    private List<Long> tagIds;
    private String title;
}
