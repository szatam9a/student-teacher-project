package learningprojectbackend.studies.service.entity.tag;

import jakarta.persistence.*;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.exercise.Exercise;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.user.User;
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
