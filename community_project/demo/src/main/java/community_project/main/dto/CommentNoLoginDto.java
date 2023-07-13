package community_project.main.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CommentNoLoginDto {
    private Long id;
    private Long boardId;
    private String content;
    private LocalDateTime createAt;
    private String state;
    private String dType;
    private String name;
    private String password;

    public CommentNoLoginDto(Long id, Long boardId, String content, LocalDateTime createAt, String state, String dType, String name, String password) {
        this.id = id;
        this.boardId = boardId;
        this.content = content;
        this.createAt = createAt;
        this.state = state;
        this.dType = dType;
        this.name = name;
        this.password = password;
    }
}
