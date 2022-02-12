package gmail.anastasiacoder.models.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Registration {
    private Integer id;
    private String token;
    private String error;
}