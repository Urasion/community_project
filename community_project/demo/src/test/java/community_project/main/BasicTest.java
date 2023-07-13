package community_project.main;

import community_project.main.dto.CategoryDto;
import community_project.main.dto.MemberDto;
import community_project.main.enums.MemberRole;
import community_project.main.repository.MemberRepository;
import community_project.main.repository.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class BasicTest {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	void 맴버테스트() {
		MemberDto memberDto = new MemberDto("wldnjs3690", "정지원", "1234", MemberRole.admin, LocalDateTime.now());
		memberRepository.save(memberDto);
		MemberDto result = memberRepository.findById("wldnjs3690");
		System.out.println(result.getName());
	}

	@Test
	void 카테고리테스트(){
		CategoryDto category = new CategoryDto("게임", null);
		categoryMapper.save(category);
	}


}
