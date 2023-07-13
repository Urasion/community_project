package community_project.main.dto;

import community_project.main.enums.MemberRole;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class MemberInfoDto {
    private String member_id;
    private String name;
    private MemberRole role;
    private LocalDateTime create_at;


    public MemberInfoDto(String member_id, String name, MemberRole role, LocalDateTime create_at) {
        this.member_id = member_id;
        this.name = name;
        this.role = role;
        this.create_at = create_at;
    }
}
