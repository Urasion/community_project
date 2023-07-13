package community_project.main.repository;

import community_project.main.dto.CommentDto;
import community_project.main.dto.CommentLoginDto;
import community_project.main.dto.CommentNoLoginDto;
import community_project.main.enums.MemberRole;
import community_project.main.repository.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentMapper commentMapper;

    @Override
    public void saveLogin(CommentDto comment, String memberId, String memberName, MemberRole memberRole) {
        commentMapper.saveComment(comment);
        log.info("commentId = {}",comment.getId());
        commentMapper.saveCommentLogin(comment.getId(), memberId, memberName, memberRole);
    }

    @Override
    public void saveNoLogin(CommentDto comment, String name, String password) {
        commentMapper.saveComment(comment);
        log.info("commentId = {}",comment.getId());
        commentMapper.saveCommentNoLogin(comment.getId(), name, password);
    }

    @Override
    public ArrayList<CommentLoginDto> findByBoardIdLogin(Long boardId) {
        return commentMapper.findByBoardIdLogin(boardId);
    }

    @Override
    public ArrayList<CommentNoLoginDto> findByBoardIdNoLogin(Long boardId) {
        return commentMapper.findByBoardIdNoLogin(boardId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentMapper.deleteComment(commentId);
    }

    @Override
    public CommentNoLoginDto findPasswordNoLogin(Long commentId) {
        return commentMapper.findPasswordNoLogin(commentId);
    }
}
