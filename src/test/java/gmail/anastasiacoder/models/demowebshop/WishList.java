package gmail.anastasiacoder.models.demowebshop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WishList {
    private String updatetopwishlistsectionhtml;
    private String message;
}