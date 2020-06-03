package spring.reflect;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserService {

    @LwhAutoWired
    private User user;

    private String usName;

}
