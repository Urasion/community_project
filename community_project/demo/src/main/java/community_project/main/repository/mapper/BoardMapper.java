package community_project.main.repository.mapper;

import community_project.main.dto.BoardDto;
import community_project.main.dto.BoardInfoDto;
import community_project.main.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface BoardMapper {
    void save(BoardDto board);
    ArrayList<BoardInfoDto> findByMemberId(@Param("id") String id, @Param("categoryId") Long categoryId, @Param("pageInfo") Criteria pageInfo);

    ArrayList<BoardInfoDto> findByMemberName(@Param("name") String name, @Param("categoryId") Long categoryId,@Param("pageInfo") Criteria pageInfo);

    ArrayList<BoardInfoDto> findByTitle( @Param("title")String title,  @Param("categoryId")Long categoryId, @Param("pageInfo")Criteria pageInfo);

    ArrayList<BoardInfoDto> findByComment( @Param("comment")String comment, @Param("categoryId") Long categoryId,@Param("pageInfo") Criteria pageInfo);

    ArrayList<BoardInfoDto> findByCategory( @Param("categoryId") Long categoryId, @Param("pageInfo")Criteria pageInfo);

    BoardInfoDto findByBoardId(@Param("boardId") Long boardId);

    Long findByMemberIdCount( @Param("id") String id,  @Param("categoryId")Long categoryId);

    Long findByMemberNameCount( @Param("name")String name,  @Param("categoryId")Long categoryId);

    Long findByTitleCount( @Param("title")String title,  @Param("categoryId")Long categoryId);

    Long findByCommentCount( @Param("comment")String comment, @Param("categoryId") Long categoryId);

    Long findByCategoryCount( @Param("categoryId") Long categoryId);

    void deleteBoard( @Param("boardId") Long boardId);
}
