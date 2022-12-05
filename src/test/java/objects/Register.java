package objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Register {

    private String email;
    private String password;

}
