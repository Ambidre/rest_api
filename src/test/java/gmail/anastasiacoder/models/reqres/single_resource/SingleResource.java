package gmail.anastasiacoder.models.reqres.single_resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleResource {
    private ResourceData data;
    private Support support;
}