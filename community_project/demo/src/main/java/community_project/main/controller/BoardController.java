package community_project.main.controller;

import community_project.main.controller.Form.BoardForm;
import community_project.main.dto.BoardInfoDto;
import community_project.main.dto.CommentFormDto;
import community_project.main.service.BoardService;
import community_project.main.service.CategoryService;
import community_project.main.service.CommentService;
import community_project.main.util.PageCreate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    @GetMapping("/board/{childId}")
    public String categoryBoard(@PathVariable("childId") Long childId, @RequestParam(value = "pageNum", required = false) Integer pageNum,@RequestParam(value = "searchType", required = false) String searchType,@RequestParam(value = "keyword", required = false) String keyword, Model model, HttpServletRequest httpServletRequest){
        log.info("갤러리 접근 로그!!");
        PageCreate pageCreate = boardService.createPage(pageNum, childId);
        String categoryName = categoryService.findByChildName(childId);
        HttpSession session = httpServletRequest.getSession(false);
        model.addAttribute("categoryId",childId);
        model.addAttribute("categoryName",categoryName);
        model.addAttribute("pageInfo",pageCreate);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        if(searchType == null){
            ArrayList<BoardInfoDto> boardResult = boardService.findByCategory(childId, pageCreate.getPageInfo());
            model.addAttribute("boardResult", boardResult);
        }
        else if(searchType.equals("title")){
            log.info("타이틀 접근!!");
            ArrayList<BoardInfoDto> boardResult = boardService.findByTitle(keyword ,childId, pageCreate.getPageInfo());
            model.addAttribute("boardResult", boardResult);


        }
        else if(searchType.equals("comment")){
            ArrayList<BoardInfoDto> boardResult = boardService.findByComment(keyword ,childId, pageCreate.getPageInfo());
            model.addAttribute("boardResult", boardResult);
        }
        else if(searchType.equals("writer")){
            ArrayList<BoardInfoDto> boardResult = boardService.findByMemberName(keyword ,childId, pageCreate.getPageInfo());
            model.addAttribute("boardResult", boardResult);
        }


        if(session == null){
            return "board/boardForm";
        }else{
            model.addAttribute("loginName",session.getAttribute("loginName"));
            return "board/loginboardForm";
        }

    }
    @GetMapping("/board/{childId}/write")
    public String boardWriteToForm(@PathVariable("childId") Long childId, @ModelAttribute("boardForm")BoardForm boardForm){
        return"/board/boardwriteForm";
    }
    @PostMapping("/board/{childId}/write")
    public String boardWriteSave(@PathVariable("childId") Long childId, @ModelAttribute("boardForm")BoardForm boardForm, HttpServletRequest httpServletRequest,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return"/board/boardwriteForm";
        }
        log.info("게시글 작성 접근");
        HttpSession session = httpServletRequest.getSession();
        String loginId = (String)session.getAttribute("loginId");
        boardService.save(boardForm,loginId,childId);
        return "redirect:/board/" + childId + "?pageNum=1";
    }
    @GetMapping("/board/{childId}/{boardId}")
    public String boardReadForm(@PathVariable("childId")Long childId, @PathVariable("boardId")Long boardId, HttpServletRequest httpServletRequest, Model model){
        BoardInfoDto byBoardId = boardService.findByBoardId(boardId);
        ArrayList<CommentFormDto> commentList = commentService.findByBoardId(boardId);
        model.addAttribute("board", byBoardId);
        model.addAttribute("commentList", commentList);
        Long viewCount = boardService.findViewCount(boardId);
        model.addAttribute("viewCount", viewCount);
        HttpSession session = httpServletRequest.getSession(false);
        if(session == null){
            log.info("비로그인 페이지 이동");
            return "board/boardreadForm";
        }else{
            log.info("로그인 페이지 이동");
            model.addAttribute("loginId",session.getAttribute("loginId"));
            model.addAttribute("loginRole",String.valueOf(session.getAttribute("loginRole")));
            return "board/loginboardreadForm";
        }

    }
    @PostMapping("/board/{childId}/{boardId}")
    public String boardDelete(@PathVariable("childId") Long childId, @PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
        return "redirect:/board/" + childId + "?pageNum=1";
    }
}
