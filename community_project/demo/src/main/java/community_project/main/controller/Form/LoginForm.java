package community_project.main.controller.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

@Data
public class LoginForm {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
