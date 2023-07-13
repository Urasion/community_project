package community_project.main.board;

import community_project.main.dto.*;
import community_project.main.enums.MemberRole;
import community_project.main.repository.BoardRepository;
import community_project.main.repository.CategoryRepository;
import community_project.main.repository.CommentRepository;
import community_project.main.repository.MemberRepository;
import community_project.main.util.Criteria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
@Transactional
public class BoardTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
    @BeforeEach
    void before_start(){
        MemberDto memberDto = new MemberDto("wldnjs3690", "정지원", "1234", MemberRole.admin, LocalDateTime.now());
        memberRepository.save(memberDto);
        MemberDto memberDto1 = new MemberDto("test", "11", "1234", MemberRole.fixed, LocalDateTime.now());
        memberRepository.save(memberDto1);
    }

    @Test
    void boardInsertTest(){
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
        System.out.println(board.getId());

    }
    @Test
    void boardSearchByMemberId(){
        Criteria criteria = new Criteria();
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
        BoardDto board1 = new BoardDto("11", "1111", "test", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board1);
        ArrayList<BoardInfoDto> result = boardRepository.findByMemberId("wldnjs3690", 1L, criteria);
        Assertions.assertThat(result.get(0).getMemberId()).isEqualTo(board.getMemberId());
    }
    @Test
    void boardSearchByMemberName(){
        Criteria criteria = new Criteria();
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
        BoardDto board1 = new BoardDto("11", "1111", "test", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board1);
        ArrayList<BoardInfoDto> result = boardRepository.findByMemberName("정지원", 1L, criteria);
        System.out.println(result.size());
        Assertions.assertThat(result.get(0).getMemberId()).isEqualTo(board.getMemberId());
    }
    @Test
    void boardSearchByTitle(){
        Criteria criteria = new Criteria();
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
        BoardDto board1 = new BoardDto("11", "1111", "test", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board1);
        ArrayList<BoardInfoDto> result = boardRepository.findByTitle("11", 1L,criteria);
        System.out.println(result.size());
        Assertions.assertThat(result.get(1).getMemberId()).isEqualTo(board.getMemberId());
        Assertions.assertThat(result.get(0).getMemberId()).isEqualTo(board1.getMemberId());
    }
    @Test
    void boardSearchByComment(){
        Criteria criteria = new Criteria();
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
        BoardDto board1 = new BoardDto("11", "1111", "test", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board1);
        ArrayList<BoardInfoDto> boardResult = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment = new CommentDto(boardResult.get(0).getId(), "노잼!", LocalDateTime.now(), "noBlind", "login");
        commentRepository.saveLogin(comment, "wldnjs3690","정지원",MemberRole.admin);
        ArrayList<BoardInfoDto> boardResult1 = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment1 = new CommentDto(boardResult1.get(1).getId(), "노잼!", LocalDateTime.now(), "noBlind", "nologin");
        commentRepository.saveNoLogin(comment1,"정지원","1234");

        ArrayList<BoardInfoDto> result = boardRepository.findByComment("노잼", 1L,criteria);
        Assertions.assertThat(result.get(0).getId()).isNotEqualTo(result.get(1).getId());

    }
    @Test
    void boardSearchByCategory(){
        Criteria criteria = new Criteria();
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
        BoardDto board1 = new BoardDto("11", "1111", "test", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board1);
        ArrayList<BoardInfoDto> result = boardRepository.findByCategory(1L,criteria);
        System.out.println(result.size());
        Assertions.assertThat(result.get(1).getMemberId()).isEqualTo(board.getMemberId());
        Assertions.assertThat(result.get(0).getMemberId()).isEqualTo(board1.getMemberId());
    }
    @Test
    void boardSearchCountTest(){
        Criteria criteria = new Criteria();
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
        BoardDto board1 = new BoardDto("11", "1111", "test", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board1);
        ArrayList<BoardInfoDto> boardResult = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment = new CommentDto(boardResult.get(0).getId(), "노잼!", LocalDateTime.now(), "noBlind", "login");
        commentRepository.saveLogin(comment, "wldnjs3690","정지원",MemberRole.admin);
        ArrayList<BoardInfoDto> boardResult1 = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment1 = new CommentDto(boardResult1.get(1).getId(), "노잼!", LocalDateTime.now(), "noBlind", "nologin");
        commentRepository.saveNoLogin(comment1,"정지원","1234");

        Long result = boardRepository.findByCategoryCount(1L);
        Long result2 = boardRepository.findByMemberIdCount("wldnjs3690", 1L);
        Long result3 = boardRepository.findByCommentCount("노잼", 1L);
        Long result4 = boardRepository.findByTitleCount("11", 1L);
        Long result5 = boardRepository.findByMemberNameCount("정지원", 1L);
        System.out.println("result = " + result);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);
        System.out.println("result4 = " + result4);
        System.out.println("result5 = " + result5);

    }
}
