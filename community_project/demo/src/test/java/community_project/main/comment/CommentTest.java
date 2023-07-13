package community_project.main.comment;

import community_project.main.dto.*;
import community_project.main.enums.MemberRole;
import community_project.main.repository.BoardRepository;
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
public class CommentTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void before_start(){
        MemberDto memberDto = new MemberDto("wldnjs3690", "정지원", "1234", MemberRole.admin, LocalDateTime.now());
        memberRepository.save(memberDto);
        MemberDto memberDto1 = new MemberDto("test", "11", "1234", MemberRole.fixed, LocalDateTime.now());
        memberRepository.save(memberDto1);
        BoardDto board = new BoardDto("11", "1111", "wldnjs3690", 0L, LocalDateTime.now(), 1L);
        boardRepository.save(board);
    }
    @Test
    void commentLoginInsertTest(){
        Criteria criteria = new Criteria();
        ArrayList<BoardInfoDto> boardResult = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment = new CommentDto(boardResult.get(0).getId(), "노잼!", LocalDateTime.now(), "noBlind", "login");
        commentRepository.saveLogin(comment, "wldnjs3690","정지원",MemberRole.admin);

    }
    @Test
    void commentNoLoginInsertTest(){
        Criteria criteria = new Criteria();
        ArrayList<BoardInfoDto> boardResult = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment = new CommentDto(boardResult.get(0).getId(), "노잼!", LocalDateTime.now(), "noBlind", "nologin");
        commentRepository.saveNoLogin(comment,"정지원","1234");
    }
    @Test
    void commentLoginFindTest(){
        Criteria criteria = new Criteria();
        ArrayList<BoardInfoDto> boardResult = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment = new CommentDto(boardResult.get(0).getId(), "노잼!", LocalDateTime.now(), "noBlind", "login");
        commentRepository.saveLogin(comment, "wldnjs3690","정지원",MemberRole.admin);
        ArrayList<CommentLoginDto> result = commentRepository.findByBoardIdLogin(boardResult.get(0).getId());
        Assertions.assertThat(result.get(0).getId()).isEqualTo(comment.getId());
    }
    @Test
    void commentNoLoginFindTest(){
        Criteria criteria = new Criteria();
        ArrayList<BoardInfoDto> boardResult = boardRepository.findByTitle("11", 1L,criteria);
        CommentDto comment = new CommentDto(boardResult.get(0).getId(), "노잼!", LocalDateTime.now(), "noBlind", "nologin");
        commentRepository.saveNoLogin(comment,"정지원","1234");
        ArrayList<CommentNoLoginDto> result = commentRepository.findByBoardIdNoLogin(boardResult.get(0).getId());
        Assertions.assertThat(result.get(0).getId()).isEqualTo(comment.getId());
    }
}
