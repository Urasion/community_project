package community_project.main.dto;

import community_project.main.controller.Form.JoinForm;
import community_project.main.enums.MemberRole;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberDto {
    private String id;
    private String name;
    private String password;
    private MemberRole role;
    private LocalDateTime joinAt;

    public MemberDto(String id, String name, String password, MemberRole role, LocalDateTime joinAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.joinAt = joinAt;
    }
    public void setMember(JoinForm joinForm, LocalDateTime localDateTime){
        this.id = joinForm.getId();
        this.name = joinForm.getName();
        this.password = joinForm.getPassword();
        if(joinForm.getFixCheck()){
            this.role = MemberRole.fixed;
        }else{
            this.role = MemberRole.unfixed;
        }
        this.joinAt = localDateTime;
    }
    public MemberDto(){}
}
