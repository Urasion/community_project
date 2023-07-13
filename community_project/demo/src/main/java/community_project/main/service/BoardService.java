package community_project.main.service;

import community_project.main.controller.Form.BoardForm;
import community_project.main.dto.BoardInfoDto;
import community_project.main.util.Criteria;
import community_project.main.util.PageCreate;

import java.util.ArrayList;

public interface BoardService {
    void save(BoardForm boardForm, String memberId, Long categoryId);


    ArrayList<BoardInfoDto> findByMemberId(String id, Long categoryId, Criteria pageInfo);

    ArrayList<BoardInfoDto> findByMemberName(String name, Long categoryId, Criteria pageInfo);

    ArrayList<BoardInfoDto> findByTitle(String title, Long categoryId, Criteria pageInfo);

    ArrayList<BoardInfoDto> findByComment(String comment, Long categoryId, Criteria pageInfo);
    Long findViewCount(Long boardId);

    ArrayList<BoardInfoDto> findByCategory(Long categoryId, Criteria pageInfo);
    Long findByMemberIdCount(String id, Long categoryId);

    Long findByMemberNameCount(String name, Long categoryId);

    Long findByTitleCount(String title, Long categoryId);

    Long findByCommentCount(String comment, Long categoryId);

    Long findByCategoryCount(Long categoryId);

    PageCreate createPage(Integer pageNum, Long childId);
    BoardInfoDto findByBoardId(Long boardId);

    void deleteBoard(Long boardId);
}
