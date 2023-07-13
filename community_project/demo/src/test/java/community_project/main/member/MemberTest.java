package community_project.main.member;

import community_project.main.dto.MemberDto;
import community_project.main.dto.MemberInfoDto;
import community_project.main.enums.MemberRole;
import community_project.main.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
@Transactional
public class MemberTest {
    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach
    void before_start(){
        MemberDto memberDto = new MemberDto("wldnjs3690", "정지원", "1234", MemberRole.admin, LocalDateTime.now());
        memberRepository.save(memberDto);
    }
    @Test
    void memberNameTest(){
        ArrayList<MemberDto> result = memberRepository.findByName("정지원");
        System.out.println("result.getName = " + result.get(0).getName());

    }
}
