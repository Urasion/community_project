package community_project.main.dto;

import lombok.Getter;

@Getter
public class CategoryDto {
    private Long id;
    private String name;
    private Long parent;

    public CategoryDto(String name, Long parent) {
        this.name = name;
        this.parent = parent;
    }

    public CategoryDto(Long id, String name, Long parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}
