package learningprojectbackend.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class CaptchaResponse {
    private Boolean success;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("challenge_ts")
    private LocalDateTime challengeTs;
    private String hostname;
}
