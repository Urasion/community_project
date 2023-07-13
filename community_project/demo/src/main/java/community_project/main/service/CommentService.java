package community_project.main.service;

import community_project.main.controller.Form.NoLoginCommentForm;
import community_project.main.dto.CommentDto;
import community_project.main.dto.CommentFormDto;
import community_project.main.dto.CommentLoginDto;
import community_project.main.dto.CommentNoLoginDto;
import community_project.main.enums.MemberRole;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface CommentService {
    void saveLogin(Long boardId, String content, String state, String memberId, String memberName, MemberRole memberRole);
    void saveNoLogin(Long boardId,  String state,NoLoginCommentForm comment);
    ArrayList<CommentFormDto> findByBoardId(Long boardId);
    void deleteComment(Long commentId);
    void deleteCommentNoLogin(Long commentId, String password);
}
