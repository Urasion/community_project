package community_project.main.category;

import community_project.main.dto.CategoryDto;
import community_project.main.repository.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SpringBootTest
@Transactional
public class CategoryTest {
    @Autowired
    private CategoryMapper categoryMapper;
    @Test
    void categoryInsertTest(){
        CategoryDto category = new CategoryDto("음악", null);
        categoryMapper.save(category);
    }
    @Test
    void categoryFindParentTest(){
        ArrayList<CategoryDto> result = categoryMapper.findByParent();
        for (CategoryDto categoryDto : result) {
            System.out.println(categoryDto.getName());
        }
    }
    @Test
    void categoryFindChildTest(){
        CategoryDto category = new CategoryDto("블루아카이브", 1L);
        categoryMapper.save(category);
        ArrayList<CategoryDto> result = categoryMapper.findByChild(1L);
        for (CategoryDto categoryDto : result) {
            System.out.println(categoryDto.getName());
        }
    }

}
