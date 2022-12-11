package objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceSupport {
    private String url;
    private String text;
}

