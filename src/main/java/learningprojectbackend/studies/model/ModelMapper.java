package learningprojectbackend.studies.model;


import learningprojectbackend.studies.model.dto.exercise.AnswerDto;
import learningprojectbackend.studies.model.dto.exercise.CreateExerciseDto;
import learningprojectbackend.studies.model.dto.exercise.ExerciseDto;
import learningprojectbackend.studies.model.dto.user.CreateUserDto;
import learningprojectbackend.studies.model.dto.user.UserDto;
import learningprojectbackend.studies.model.entity.exercise.Answer.Answer;
import learningprojectbackend.studies.model.entity.exercise.Exercise;
import learningprojectbackend.studies.model.entity.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {


    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> user);

    User toUser(CreateUserDto createUserDto);

    ExerciseDto toExerciseDto(Exercise exercise);

    List<ExerciseDto> toExerciseDto(List<Exercise> exercises);

    Exercise toExercise(CreateExerciseDto createExerciseDto);

    AnswerDto toAnswerDto(Answer answer);

    List<AnswerDto> toAnswerDto(List<Answer> answerList);
}
