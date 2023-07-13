package community_project.main.service;

import community_project.main.dto.CategoryDto;
import community_project.main.enums.MemberRole;

import java.util.ArrayList;

public interface CategoryService {
    boolean save(CategoryDto category, MemberRole memberRole);
    String findByParentName(Long parentId);
    String findByChildName(Long childId);
    ArrayList<CategoryDto> findByParent();
    ArrayList<CategoryDto> findByChild(Long parentId);
}
