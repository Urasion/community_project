package community_project.main.repository;

import community_project.main.dto.CommentDto;
import community_project.main.dto.CommentLoginDto;
import community_project.main.dto.CommentNoLoginDto;
import community_project.main.enums.MemberRole;
import community_project.main.repository.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface CommentRepository {

    void saveLogin(CommentDto comment, String memberId, String memberName, MemberRole memberRole);
    void saveNoLogin(CommentDto comment, String name, String password);
    ArrayList<CommentLoginDto> findByBoardIdLogin(Long boardId);
    ArrayList<CommentNoLoginDto> findByBoardIdNoLogin(Long boardId);
    void deleteComment( Long commentId);
    CommentNoLoginDto findPasswordNoLogin(@Param("commentId") Long commentId);

}
