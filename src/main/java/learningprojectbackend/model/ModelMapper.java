package learningprojectbackend.model;


import learningprojectbackend.model.dto.exercise.ExerciseDto;
import learningprojectbackend.model.dto.user.CreateUserDto;
import learningprojectbackend.model.dto.user.UserDto;
import learningprojectbackend.model.entity.User;
import learningprojectbackend.model.entity.exercise.Exercise;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelMapper {


    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> user);

    User toUser (CreateUserDto createUserDto);

    ExerciseDto toExerciseDto(Exercise exercise);
    List<ExerciseDto> toExerciseDto(List<Exercise> exercises);
}
