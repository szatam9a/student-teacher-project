package learningprojectbackend.studies.model;


import learningprojectbackend.studies.controller.exercise.AnswerDto;
import learningprojectbackend.studies.controller.exercise.CreateExerciseDto;
import learningprojectbackend.studies.controller.exercise.ExerciseDto;
import learningprojectbackend.studies.controller.tag.CreateTagDto;
import learningprojectbackend.studies.controller.tag.TagDto;
import learningprojectbackend.studies.controller.user.RegistrationRequest;
import learningprojectbackend.studies.controller.user.UserDto;
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

    Tag toTag(CreateTagDto createTagDto);

    TagDto toTagDto(Tag tag);

    List<TagDto> toTagDto(List<Tag> tag);

    ExerciseDto toExerciseDto(Exercise exercise);

    List<ExerciseDto> toExerciseDto(List<Exercise> exercises);

    Exercise toExercise(CreateExerciseDto createExerciseDto);

    AnswerDto toAnswerDto(Answer answer);

    List<AnswerDto> toAnswerDto(List<Answer> answerList);
}
