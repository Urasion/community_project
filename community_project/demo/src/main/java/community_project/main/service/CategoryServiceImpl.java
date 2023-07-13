package community_project.main.service;

import community_project.main.dto.CategoryDto;
import community_project.main.enums.MemberRole;
import community_project.main.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public boolean save(CategoryDto category, MemberRole memberRole) {
        if(category.getParent() != null){
            if(categoryRepository.findByParentId(category.getParent()) != null){
                categoryRepository.save(category);
                return true;
            }else{
                return false;
            }
        }else{
            if(memberRole == MemberRole.admin){
                categoryRepository.save(category);
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public String findByParentName(Long parentId) {
        CategoryDto category = categoryRepository.findByParentId(parentId);
        return category.getName();
    }

    @Override
    public String findByChildName(Long childId) {
        CategoryDto category = categoryRepository.findByChildId(childId);
        return category.getName();
    }

    @Override
    public ArrayList<CategoryDto> findByParent(){
       return categoryRepository.findByParent();
    }

    @Override
    public ArrayList<CategoryDto> findByChild(Long parentId) {

        return categoryRepository.findByChild(parentId);
    }
}
