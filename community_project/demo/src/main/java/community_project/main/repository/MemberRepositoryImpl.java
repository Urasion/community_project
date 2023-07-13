package community_project.main.repository;

import community_project.main.dto.MemberDto;
import community_project.main.dto.MemberInfoDto;
import community_project.main.repository.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final MemberMapper memberMapper;
    @Override
    public void save(MemberDto member) {
        memberMapper.save(member);
    }

    @Override
    public MemberDto findById(String id) {
        return memberMapper.findById(id);
    }
    /**
     *  유동닉같은 경우 여러개가 검색될 여지가 있으므로 리스트로 가져옴
     */
    @Override
    public ArrayList<MemberDto> findByName(String name) {
        return memberMapper.findByName(name);
    }
}
