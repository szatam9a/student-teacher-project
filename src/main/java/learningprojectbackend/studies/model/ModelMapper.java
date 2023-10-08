package learningprojectbackend.studies.model;


import learningprojectbackend.studies.controller.course.CourseDto;
import learningprojectbackend.studies.controller.course.CourseFilterDto;
import learningprojectbackend.studies.controller.course.LessonDto;
import learningprojectbackend.studies.controller.course.LessonFilterDto;
import learningprojectbackend.studies.controller.exercise.AnswerDto;
import learningprojectbackend.studies.controller.exercise.CreateExerciseRequest;
import learningprojectbackend.studies.controller.exercise.ExerciseDto;
import learningprojectbackend.studies.controller.studerial.StuderialDto;
import learningprojectbackend.studies.controller.tag.CreateTagRequest;
import learningprojectbackend.studies.controller.tag.TagDto;
import learningprojectbackend.studies.controller.user.RegistrationRequest;
import learningprojectbackend.studies.controller.user.UserDto;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.exercise.Answer;
import learningprojectbackend.studies.service.entity.exercise.Exercise;
import learningprojectbackend.studies.service.entity.studerial.Studerial;
import learningprojectbackend.studies.service.entity.tag.Tag;
import learningprojectbackend.studies.service.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ModelMapper {


    User toUser(RegistrationRequest registrationRequest);

    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> user);

    Set<UserDto> toUserDto(Set<User> user);


    Tag toTag(CreateTagRequest createTagRequest);

    TagDto toTagDto(Tag tag);

    List<TagDto> toTagDto(List<Tag> tag);

    Set<TagDto> toTagDto(Set<Tag> tag);

    LessonDto toLessonDto(Lesson lesson);

    List<LessonDto> toLessonDto(List<Lesson> lesson);

    Set<LessonDto> toLessonDto(Set<Lesson> lesson);

    LessonFilterDto toLessonFilterDto(Lesson lesson);

    CourseDto toCourseDto(Course course);

    List<CourseDto> toCourseDto(List<Course> course);

    Set<CourseDto> toCourseDto(Set<Course> course);

    CourseFilterDto toCourseFilterDto(Course course);

    Exercise toExercise(CreateExerciseRequest createExerciseRequest);

    ExerciseDto toExerciseDto(Exercise exercise);

    List<ExerciseDto> toExerciseDto(List<Exercise> exercises);

    Set<ExerciseDto> toExerciseDto(Set<Exercise> exercises);

    AnswerDto toAnswerDto(Answer answer);

    List<AnswerDto> toAnswerDto(List<Answer> answerList);

    StuderialDto toStuderialDto(Studerial studerial);

    List<StuderialDto> toStuderialDto(List<Studerial> studerials);

    Set<StuderialDto> toStuderialDto(Set<Studerial> studerials);
}
