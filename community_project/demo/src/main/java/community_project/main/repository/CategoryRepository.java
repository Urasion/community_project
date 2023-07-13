package community_project.main.repository;

import community_project.main.dto.CategoryDto;

import java.util.ArrayList;

public interface CategoryRepository {
    void save(CategoryDto category);

    ArrayList<CategoryDto> findByParent();
    CategoryDto findByParentId(Long parentId);
    CategoryDto findByChildId(Long childId);
    ArrayList<CategoryDto> findByChild(Long parentId);

}
