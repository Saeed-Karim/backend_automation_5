package api.pojo_classes.go_rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateGoRestUserWithLombok {

    /**
     * {
     * "gender": "",
     * "email": "Ophelia.Mraz@hotmail.com",
     * "status": ""
     * }
     */

    private String gender;
    private String email;
    private String status;

}
