package community_project.main.controller;

import community_project.main.controller.Form.NoLoginCommentForm;
import community_project.main.enums.MemberRole;
import community_project.main.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{childId}/{boardId}")
    public String commentNoLoginWrite(@PathVariable("childId") Long childId, @PathVariable("boardId")Long boardId, @ModelAttribute("NoLoginCommentForm")NoLoginCommentForm comment){
        log.info("댓글 작성 진입");
        commentService.saveNoLogin(boardId,"none" ,comment);
        return "redirect:/board/"+childId+"/"+boardId;
    }
    @PostMapping("/commentL/{childId}/{boardId}")
    public String commentLoginWrite(@PathVariable("childId") Long childId, @PathVariable("boardId")Long boardId, @RequestParam("content")String content, HttpServletRequest httpServletRequest){
        log.info("댓글 작성 진입");
        HttpSession session = httpServletRequest.getSession();
        commentService.saveLogin(boardId, content, "none",(String)session.getAttribute("loginId"),(String)session.getAttribute("loginName"),(MemberRole) session.getAttribute("loginRole"));
        return "redirect:/board/"+childId+"/"+boardId;
    }
    @PostMapping("/comment/{childId}/{boardId}/{commentId}")
    public String commentDelete(@PathVariable("childId") Long childId, @PathVariable("boardId")Long boardId,@RequestParam(value = "commentPassword", required = false) String commentPassword,@PathVariable("commentId")Long commentId,
                                @RequestParam("dType") String dType, @RequestParam(value = "loginRole", required = false)String loginRole){
        log.info("댓글 삭제 진입");
        if(dType.equals("login") || loginRole.equals("admin")){
            commentService.deleteComment(commentId);
        } else if (dType.equals("nologin")) {
            commentService.deleteCommentNoLogin(commentId, commentPassword);
        }
        return "redirect:/board/"+childId+"/"+boardId;
    }


}
