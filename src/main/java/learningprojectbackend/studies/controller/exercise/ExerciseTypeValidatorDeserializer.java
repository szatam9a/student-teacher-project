package learningprojectbackend.studies.controller.exercise;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import learningprojectbackend.studies.controller.ExerciseController;
import learningprojectbackend.studies.service.entity.exercise.ExerciseType;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.IOException;

public class ExerciseTypeValidatorDeserializer extends JsonDeserializer<ExerciseType> {
    @Override
    @SneakyThrows
    public ExerciseType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.getValueAsString();
        for (ExerciseType exerciseType : (ExerciseType.values())) {
            if (exerciseType.getValue().equals(value)) {
                return exerciseType;
            }
        }
        BindingResult bindingResult = new BeanPropertyBindingResult(
                ExerciseType.class, "exerciseTypeClass");
        bindingResult.addError(new FieldError("exerciseType", "exerciseType", "exerciseType invalid:" + value));
        MethodParameter methodParameter = new MethodParameter(ExerciseController.class.getMethod("createExercise", CreateExerciseRequest.class), 0);
        throw new MethodArgumentNotValidException(methodParameter, bindingResult);
    }
}
