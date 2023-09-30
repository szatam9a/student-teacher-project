package learningprojectbackend.auth.service;

import learningprojectbackend.auth.model.dto.CaptchaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CaptchaService {

    @Value("${captcha.secretKey}")
    private String secretKey;
    @Value("${captcha.verifyUrl}")
    private String verifyUrl;
    private final RestTemplate restTemplate;

    public CaptchaResponse validateToken(String captchaToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("secret", secretKey);
        body.add("response", captchaToken);
        HttpEntity<MultiValueMap<String, String>> captchaDetails = new HttpEntity<>(body, headers);
        return restTemplate.exchange(verifyUrl, HttpMethod.POST, captchaDetails, CaptchaResponse.class).getBody();
    }
}
