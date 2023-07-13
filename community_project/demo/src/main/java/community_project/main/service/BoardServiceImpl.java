package community_project.main.service;

import community_project.main.controller.Form.BoardForm;
import community_project.main.dto.BoardDto;
import community_project.main.dto.BoardInfoDto;
import community_project.main.dto.redis.ViewEntity;
import community_project.main.repository.BoardRepository;
import community_project.main.repository.redis.ViewRedisRepository;
import community_project.main.util.Criteria;
import community_project.main.util.PageCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final ViewRedisRepository viewRedisRepository;
    @Override
    public ArrayList<BoardInfoDto> findByCategory(Long categoryId, Criteria pageInfo) {
        return boardRepository.findByCategory(categoryId, pageInfo);
    }

    @Override
    public void save(BoardForm boardForm, String memberId, Long categoryId) {
        BoardDto boardDto = new BoardDto(boardForm.getTitle(), boardForm.getContent(), memberId, 0L, LocalDateTime.now(), categoryId);
        boardRepository.save(boardDto);
    }

    @Override
    public void deleteBoard(Long boardId) {
        boardRepository.deleteBoard(boardId);
    }

    @Override
    public Long findViewCount(Long boardId) {
        log.info("페이지 카운트 페이즈 접근!");
        Optional<ViewEntity> byId = viewRedisRepository.findById(boardId);
        if(!byId.isPresent()){
            viewRedisRepository.save(new ViewEntity(boardId, 1L));
            return 1L;
        }else{
            log.info("검색결과 : {}",byId.get());
            Long viewCount = byId.get().getViewCount();
            viewRedisRepository.save(new ViewEntity(boardId, viewCount+1));
            return viewCount+1;

        }
    }

    @Override
    public ArrayList<BoardInfoDto> findByMemberId(String id, Long categoryId, Criteria pageInfo) {
        return boardRepository.findByMemberId(id, categoryId, pageInfo);
    }

    @Override
    public ArrayList<BoardInfoDto> findByMemberName(String name, Long categoryId, Criteria pageInfo) {
        return boardRepository.findByMemberName(name, categoryId, pageInfo);
    }

    @Override
    public ArrayList<BoardInfoDto> findByTitle(String title, Long categoryId, Criteria pageInfo) {
        return boardRepository.findByTitle(title, categoryId, pageInfo);
    }

    @Override
    public ArrayList<BoardInfoDto> findByComment(String comment, Long categoryId, Criteria pageInfo) {
        return  boardRepository.findByComment(comment, categoryId, pageInfo);
    }

    @Override
    public Long findByMemberIdCount(String id, Long categoryId) {
        return  boardRepository.findByMemberIdCount(id, categoryId);
    }

    @Override
    public Long findByMemberNameCount(String name, Long categoryId) {
        return  boardRepository.findByMemberNameCount(name, categoryId);
    }

    @Override
    public Long findByTitleCount(String title, Long categoryId) {
        return  boardRepository.findByTitleCount(title, categoryId);
    }

    @Override
    public Long findByCommentCount(String comment, Long categoryId) {
        return  boardRepository.findByCommentCount(comment, categoryId);
    }

    @Override
    public Long findByCategoryCount(Long categoryId) {
        return  boardRepository.findByCategoryCount(categoryId);
    }

    @Override
    public PageCreate createPage(Integer pageNum, Long childId) {
        PageCreate pageCreate = new PageCreate();
        Criteria pageInfo = new Criteria(pageNum, 10);
        Long boardAmount = boardRepository.findByCategoryCount(childId);
        pageCreate.setBoardAmount(pageInfo, boardAmount);
        return pageCreate;

    }

    @Override
    public BoardInfoDto findByBoardId(Long boardId) {
        BoardInfoDto result = boardRepository.findByBoardId(boardId);
        return result;
    }
}
