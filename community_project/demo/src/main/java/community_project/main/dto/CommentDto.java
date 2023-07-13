package community_project.main.dto;

import community_project.main.enums.MemberRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private Long id;
    private Long boardId;
    private String content;
    private LocalDateTime createAt;
    private String state;
    private String dType;

    public CommentDto(Long boardId, String content, LocalDateTime createAt, String state, String dType) {
        this.boardId = boardId;
        this.content = content;
        this.createAt = createAt;
        this.state = state;
        this.dType = dType;
    }
}
