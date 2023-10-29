package learningprojectbackend.studies.model;


import learningprojectbackend.studies.controller.course.CourseDto;
import learningprojectbackend.studies.controller.course.LessonDto;
import learningprojectbackend.studies.controller.exercise.*;
import learningprojectbackend.studies.controller.tag.CreateTagRequest;
import learningprojectbackend.studies.controller.tag.TagDto;
import learningprojectbackend.studies.controller.user.RegistrationRequest;
import learningprojectbackend.studies.controller.user.UserDto;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.exercise.Answer;
import learningprojectbackend.studies.service.entity.exercise.Exercise;
import learningprojectbackend.studies.service.entity.tag.Tag;
import learningprojectbackend.studies.service.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {


    User toUser(RegistrationRequest registrationRequest);

    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> user);

    Tag toTag(CreateTagRequest createTagRequest);

    TagDto toTagDto(Tag tag);

    List<TagDto> toTagDto(List<Tag> tag);

    LessonDto toLessonDto(Lesson lesson);

    List<LessonDto> toLessonDto(List<Lesson> lesson);

    CourseDto toCourseDto(Course course);

    List<CourseDto> toCourseDto(List<Course> course);

    Exercise toExercise(CreateExerciseRequest createExerciseRequest);

    ExerciseDto toExerciseDto(Exercise exercise);

    List<ExerciseDto> toExerciseDto(List<Exercise> exercises);

    AnswerDto toAnswerDto(Answer answer);

    AnswerIdDto toAnswerIdDto(Answer answer);

    List<AnswerDto> toAnswerDto(List<Answer> answerList);

    ExerciseFilterDto toExerciseFilterDto(Exercise exercise);
}
