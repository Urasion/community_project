package community_project.main.controller.Form;

import community_project.main.enums.MemberRole;
import community_project.main.repository.BoardRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class JoinForm {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private boolean fixCheck;

    public boolean getFixCheck(){
        return fixCheck;
    }
}
