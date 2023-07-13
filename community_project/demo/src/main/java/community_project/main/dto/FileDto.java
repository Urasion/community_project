package community_project.main.dto;

import lombok.Getter;

@Getter
public class FileDto {
    private Long id;
    private Long board_id;
    private String path;
    private String name;
    private String type;
    private String size;
}
