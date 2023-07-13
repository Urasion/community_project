package community_project.main.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class BoardInfoDto {
    private Long id;
    private String title;
    private String content;
    private String memberId;
    private String memberName;
    private Long view;
    private LocalDateTime createAt;
    private Long categoryId;

    public BoardInfoDto(Long id, String title, String content, String memberId, String memberName, Long view, LocalDateTime createAt, Long categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
        this.view = view;
        this.createAt = createAt;
        this.categoryId = categoryId;
    }
}
