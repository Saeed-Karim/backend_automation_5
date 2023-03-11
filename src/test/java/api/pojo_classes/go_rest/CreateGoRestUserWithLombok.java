package api.pojo_classes.go_rest;

import lombok.Builder;
import lombok.Data;
/**
 * With the help of data which is coming from Lombok,
 * we can eliminate the getters and setters
 */
@Data
@Builder
/**
 * With @Builder, we are able to assign
 */

public class CreateGoRestUserWithLombok {

    private String name;
    private String gender;
    private String email;
    private String status;

}
