package learningprojectbackend.studies.model;


import learningprojectbackend.studies.controller.dto.exercise.AnswerDto;
import learningprojectbackend.studies.controller.dto.exercise.CreateExerciseDto;
import learningprojectbackend.studies.controller.dto.exercise.ExerciseDto;
import learningprojectbackend.studies.controller.dto.tag.CreateTagDto;
import learningprojectbackend.studies.controller.dto.tag.TagDto;
import learningprojectbackend.studies.controller.dto.user.RegistrationRequest;
import learningprojectbackend.studies.controller.dto.user.UserDto;
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
