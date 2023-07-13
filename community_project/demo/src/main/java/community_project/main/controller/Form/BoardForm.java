package community_project.main.controller.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BoardForm {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

}
