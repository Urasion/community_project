package community_project.main.repository;

import community_project.main.dto.MemberDto;
import community_project.main.dto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


public interface MemberRepository {
    void save(MemberDto member);
    MemberDto findById(String id);

    ArrayList<MemberDto> findByName(String name);
}
