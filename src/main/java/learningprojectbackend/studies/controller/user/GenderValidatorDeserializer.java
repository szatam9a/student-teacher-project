package learningprojectbackend.studies.controller.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import learningprojectbackend.studies.controller.UserController;
import learningprojectbackend.studies.service.entity.user.Gender;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class GenderValidatorDeserializer extends JsonDeserializer<Gender> {

    @SneakyThrows
    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        String value = jsonParser.getValueAsString();
        for (Gender gender : (Gender.values())) {
            if (gender.getValue().equals(value)) {
                return gender;
            }
        }
        BindingResult bindingResult = new BeanPropertyBindingResult(
                Gender.class, "genderClass");
        bindingResult.addError(new FieldError("gender", "gender", "gender invalid:" + value));
        MethodParameter methodParameter = new MethodParameter(UserController.class.getMethod("createNewUser", RegistrationRequest.class), 0);
        throw new MethodArgumentNotValidException(methodParameter, bindingResult);
    }
}
