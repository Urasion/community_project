package community_project.main.dto;

import community_project.main.enums.MemberRole;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentLoginDto {
    private Long id;
    private Long boardId;
    private String content;
    private LocalDateTime createAt;
    private String state;
    private String dType;
    private String memberId;
    private MemberRole memberRole;
    private String memberName;

    public CommentLoginDto(Long id, Long boardId, String content, LocalDateTime createAt, String state, String dType, String memberId, MemberRole memberRole, String memberName) {
        this.id = id;
        this.boardId = boardId;
        this.content = content;
        this.createAt = createAt;
        this.state = state;
        this.dType = dType;
        this.memberId = memberId;
        this.memberRole = memberRole;
        this.memberName = memberName;
    }
}
