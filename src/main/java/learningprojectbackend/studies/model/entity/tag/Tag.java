package learningprojectbackend.studies.model.entity.tag;

import jakarta.persistence.*;
import learningprojectbackend.studies.model.entity.course.Course;
import learningprojectbackend.studies.model.entity.course.Lesson;
import learningprojectbackend.studies.model.entity.exercise.Exercise;
import learningprojectbackend.studies.model.entity.studerial.Studerial;
import learningprojectbackend.studies.model.entity.user.User;
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
    private List<Studerial> studerialList;
    @ManyToMany(mappedBy = "tagList")
    private List<Exercise> exerciseList;
    @ManyToMany(mappedBy = "tagList")
    private List<Course> courseList;
    @ManyToMany(mappedBy = "tagList")
    private List<Lesson> lessonList;
    @ManyToOne
    private User user;
}
