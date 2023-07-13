package community_project.main.repository.mapper;

import community_project.main.dto.CommentDto;
import community_project.main.dto.CommentLoginDto;
import community_project.main.dto.CommentNoLoginDto;
import community_project.main.dto.MemberInfoDto;
import community_project.main.enums.MemberRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface CommentMapper {
    Long saveComment(@Param("comment") CommentDto comment);

    void saveCommentLogin(@Param("commentId") Long commentId, @Param("memberId")String memberId, @Param("memberName")String memberName, @Param("memberRole")MemberRole memberRole);

    void saveCommentNoLogin(@Param("commentId")Long commentId,  @Param("name")String name,  @Param("password")String password);

    ArrayList<CommentLoginDto> findByBoardIdLogin( @Param("boardId")Long boardId);
    ArrayList<CommentNoLoginDto> findByBoardIdNoLogin( @Param("boardId")Long boardId);

    void deleteComment(@Param("commentId") Long commentId);

    CommentNoLoginDto findPasswordNoLogin(@Param("commentId") Long commentId);
}
