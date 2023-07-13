package community_project.main.repository.mapper;

import community_project.main.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface CategoryMapper {
    void save(CategoryDto category);
    ArrayList<CategoryDto> findByParent();
    CategoryDto findByParentId(@Param("parentId") Long parentId);

    CategoryDto findByChildId(@Param("childId") Long childId);

    ArrayList<CategoryDto> findByChild(@Param("parentId") Long parentId);

}
