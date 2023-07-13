package community_project.main.service;

import community_project.main.controller.Form.NoLoginCommentForm;
import community_project.main.dto.CommentDto;
import community_project.main.dto.CommentFormDto;
import community_project.main.dto.CommentLoginDto;
import community_project.main.dto.CommentNoLoginDto;
import community_project.main.enums.MemberRole;
import community_project.main.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public void saveLogin(Long boardId, String content, String state,String memberId, String memberName, MemberRole memberRole) {
        CommentDto comment = new CommentDto(boardId, content, LocalDateTime.now(), state, "login");
        commentRepository.saveLogin(comment, memberId, memberName, memberRole);
    }

    @Override
    public void saveNoLogin(Long boardId, String state,NoLoginCommentForm comment) {
        CommentDto nologin = new CommentDto(boardId, comment.getContent(), LocalDateTime.now(), state, "nologin");
        commentRepository.saveNoLogin(nologin, comment.getName(), comment.getPassword());
    }

    @Override
    public ArrayList<CommentFormDto> findByBoardId(Long boardId) {
        ArrayList<CommentFormDto> result = new ArrayList<>();
        ArrayList<CommentNoLoginDto> byBoardIdNoLogin = commentRepository.findByBoardIdNoLogin(boardId);
        ArrayList<CommentLoginDto> byBoardIdLogin = commentRepository.findByBoardIdLogin(boardId);
        for(CommentNoLoginDto comment : byBoardIdNoLogin){
            result.add(new CommentFormDto(comment.getId(), comment.getBoardId(), comment.getContent(), comment.getName(), null, comment.getCreateAt(),comment.getState(), comment.getDType(), null));
        }
        for(CommentLoginDto comment : byBoardIdLogin){
            result.add(new CommentFormDto(comment.getId(), comment.getBoardId(), comment.getContent(), comment.getMemberName(), comment.getMemberId(), comment.getCreateAt(),comment.getState(), comment.getDType(), String.valueOf(comment.getMemberRole())));
        }
        return result;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteComment(commentId);
        log.info("로그인 댓글 삭제 페이즈");
    }

    @Override
    public void deleteCommentNoLogin(Long commentId, String password) {
        log.info("비로그인 댓글 삭제 페이즈");
        CommentNoLoginDto passwordNoLogin = commentRepository.findPasswordNoLogin(commentId);
        if(passwordNoLogin.getPassword().equals(password)){
            commentRepository.deleteComment(commentId);
        }
    }
}
