package community_project.main.repository;

import community_project.main.dto.CategoryDto;
import community_project.main.repository.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryMapper categoryMapper;

    @Override
    public void save(CategoryDto category) {
         categoryMapper.save(category);
    }

    @Override
    public CategoryDto findByChildId(Long childId) {
        return categoryMapper.findByChildId(childId);
    }

    @Override
    public CategoryDto findByParentId(Long parentId) {
        return categoryMapper.findByParentId(parentId);
    }

    @Override
    public ArrayList<CategoryDto> findByParent() {
        return categoryMapper.findByParent();
    }

    @Override
    public ArrayList<CategoryDto> findByChild(Long parentId) {
        return categoryMapper.findByChild(parentId);
    }
}
