package community_project.main.dto;

import community_project.main.enums.MemberRole;
import lombok.Getter;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
@Getter
public class CommentFormDto {
    private Long id;
    private Long boardId;
    private String content;
    private String name;
    private String memberId;
    private LocalDateTime createAt;
    private String state;
    private String dType;
    private String memberRole;

    public CommentFormDto(Long id, Long boardId, String content, String name, String memberId, LocalDateTime createAt, String state, String dType, String memberRole) {
        this.id = id;
        this.boardId = boardId;
        this.content = content;
        this.name = name;
        this.memberId = memberId;
        this.createAt = createAt;
        this.state = state;
        this.dType = dType;
        this.memberRole = memberRole;
    }
}
