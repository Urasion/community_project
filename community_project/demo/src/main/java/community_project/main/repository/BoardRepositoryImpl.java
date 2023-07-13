package community_project.main.repository;

import community_project.main.dto.BoardDto;
import community_project.main.dto.BoardInfoDto;
import community_project.main.repository.mapper.BoardMapper;
import community_project.main.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository{
    private final BoardMapper boardMapper;

    @Override
    public void save(BoardDto board) {
         boardMapper.save(board);
    }

    @Override
    public ArrayList<BoardInfoDto> findByCategory(Long categoryId, Criteria pageInfo) {
        return boardMapper.findByCategory(categoryId, pageInfo);
    }

    @Override
    public ArrayList<BoardInfoDto> findByMemberId(String id, Long categoryId, Criteria pageInfo) {
        return boardMapper.findByMemberId(id, categoryId,  pageInfo);
    }

    @Override
    public ArrayList<BoardInfoDto> findByMemberName(String name, Long categoryId, Criteria pageInfo) {
        return boardMapper.findByMemberName(name, categoryId,  pageInfo);
    }

    @Override
    public ArrayList<BoardInfoDto> findByTitle(String title, Long categoryId, Criteria pageInfo) {
        return boardMapper.findByTitle(title, categoryId, pageInfo);
    }

    @Override
    public ArrayList<BoardInfoDto> findByComment(String comment, Long categoryId, Criteria pageInfo) {
        return boardMapper.findByComment(comment, categoryId, pageInfo);
    }

    @Override
    public Long findByMemberIdCount(String id, Long categoryId) {
        return boardMapper.findByMemberIdCount(id,categoryId);
    }

    @Override
    public Long findByMemberNameCount(String name, Long categoryId) {
        return boardMapper.findByMemberNameCount(name,categoryId);
    }

    @Override
    public Long findByTitleCount(String title, Long categoryId) {
        return boardMapper.findByTitleCount(title,categoryId);
    }

    @Override
    public Long findByCommentCount(String comment, Long categoryId) {
        return boardMapper.findByCommentCount(comment,categoryId);
    }

    @Override
    public Long findByCategoryCount(Long categoryId) {
        return boardMapper.findByCategoryCount(categoryId);
    }

    @Override
    public BoardInfoDto findByBoardId(Long boardId) {
        return boardMapper.findByBoardId(boardId);
    }

    @Override
    public void deleteBoard(Long boardId) {
        boardMapper.deleteBoard(boardId);
    }
}
