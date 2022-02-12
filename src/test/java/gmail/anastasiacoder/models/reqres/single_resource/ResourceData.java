package gmail.anastasiacoder.models.reqres.single_resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceData {
    private String color;
    private Long id;
    private String name;
    @JsonProperty("pantone_value")
    private String pantoneValue;
    private Long year;
}