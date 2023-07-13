package community_project.main.repository;

import community_project.main.dto.BoardDto;
import community_project.main.dto.BoardInfoDto;
import community_project.main.util.Criteria;

import java.util.ArrayList;

public interface BoardRepository {
    void save(BoardDto board);
    ArrayList<BoardInfoDto> findByMemberId(String id, Long categoryId, Criteria pageInfo);

    ArrayList<BoardInfoDto> findByMemberName(String name, Long categoryId, Criteria pageInfo);

    ArrayList<BoardInfoDto> findByTitle(String title, Long categoryId, Criteria pageInfo);

    ArrayList<BoardInfoDto> findByComment(String comment, Long categoryId, Criteria pageInfo);

    ArrayList<BoardInfoDto> findByCategory(Long categoryId, Criteria pageInfo);
    Long findByMemberIdCount(String id, Long categoryId);

    Long findByMemberNameCount(String name, Long categoryId);

    Long findByTitleCount(String title, Long categoryId);

    Long findByCommentCount(String comment, Long categoryId);

    Long findByCategoryCount(Long categoryId);
    BoardInfoDto findByBoardId(Long boardId);
    void deleteBoard(Long boardId);
}
