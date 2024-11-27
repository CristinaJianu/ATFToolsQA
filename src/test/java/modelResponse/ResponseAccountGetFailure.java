package modelResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseAccountGetFailure {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
}
