package learningprojectbackend.model.entity.tag;

import jakarta.persistence.*;
import learningprojectbackend.model.entity.course.Course;
import learningprojectbackend.model.entity.course.Lesson;
import learningprojectbackend.model.entity.exercise.Exercise;
import learningprojectbackend.model.entity.studyrial.Studyrial;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String backgroundColor;
    private String textColor;
    private String name;
    @ManyToMany(mappedBy = "tagList")
    private List<Studyrial> studyrialList;
    @ManyToMany(mappedBy = "tagList")
    private List<Exercise> exerciseList;
    @ManyToMany(mappedBy = "tagList")
    private List<Course> courseList;
    @ManyToMany(mappedBy = "tagList")
    private List<Lesson> lessonList;
}
