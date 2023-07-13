package community_project.main.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDto {
  private Long id;
  private String title;
  private String content;
  private String memberId;
  private Long view;
  private LocalDateTime createAt;
  private Long categoryId;

  public BoardDto(String title, String content, String memberId, Long view, LocalDateTime createAt, Long categoryId) {
    this.title = title;
    this.content = content;
    this.memberId = memberId;
    this.view = view;
    this.createAt = createAt;
    this.categoryId = categoryId;
  }

}
