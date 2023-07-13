package community_project.main.controller;

import community_project.main.dto.CategoryDto;
import community_project.main.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public String mainPage(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        log.info("Access mainPage");
        ArrayList<CategoryDto> result = categoryService.findByParent();
        model.addAttribute("parentCategory", result);
        if(session == null){
            return "home";
        }else{
            model.addAttribute("loginName",session.getAttribute("loginName"));
            return "loginhome";
        }

    }
    @GetMapping("/{parentId}")
    public String childPage(@PathVariable("parentId") Long parentId, Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        String parentName = categoryService.findByParentName(parentId);
        ArrayList<CategoryDto> result = categoryService.findByChild(parentId);
        model.addAttribute("parentName",parentName);
        model.addAttribute("parentId", parentId);
        model.addAttribute("childCategory", result);
        if(session == null){
            return "category/childcategory";
        }else{
            model.addAttribute("loginName",session.getAttribute("loginName"));
            return "category/loginchildcategory";
        }
    }

}
