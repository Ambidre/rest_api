package gmail.anastasiacoder.models.reqres;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {
    private String createdAt;
    private String id;
    private String job;
    private String name;
}