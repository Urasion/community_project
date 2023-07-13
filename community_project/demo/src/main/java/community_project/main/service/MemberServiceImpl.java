package community_project.main.service;

import community_project.main.controller.Form.JoinForm;
import community_project.main.dto.MemberDto;
import community_project.main.enums.MemberRole;
import community_project.main.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public MemberDto login(String id, String password) {
        MemberDto member = memberRepository.findById(id);
        if(member == null){
            log.warn("검색된 회원ID가 없습니다.");
            return null;
        }
        if(member.getPassword().equals(password)){
            return member;
        } else{
            log.warn("비밀번호가 일치하지 않습니다. {},{}",member.getPassword(), password);
            return null;
        }
    }

    /**
     *
     * 조건 1 = 중복 ID 검사
     * 조건 2 = 고닉일 경우 중복 닉네임 검사
     * 조건1,조건2 가 맞아야 회원가입 가능
     */
    @Override
    public boolean join(JoinForm joinForm) {
        MemberDto member = new MemberDto();
        member.setMember(joinForm, LocalDateTime.now());
        boolean result1 = validDuplicateMemberId(member);
        boolean result2 = true;
        if(member.getRole() == MemberRole.fixed){
            result2 = validDuplicateFixMember(member);
        }else if(member.getRole() == MemberRole.unfixed){
            result2 = validDuplicateUnFixMember(member);
        }
        if(result1 && result2){
            memberRepository.save(member);
            return true;
        } else{
            return false;
        }
    }

    /**
     *
     * 맴버아이디 중복검사
     */
    private boolean validDuplicateMemberId(MemberDto member) {
        MemberDto result = memberRepository.findById(member.getId());
        if(result != null){
            return false;
        }
        return true;
    }

    /**
     *
     * 고정닉 닉네임 중복검사
     */
    private boolean validDuplicateFixMember(MemberDto member) {
        ArrayList<MemberDto> result = memberRepository.findByName(member.getName());
        if(!result.isEmpty()){
            return false;
        }
        return true;
    }
    private boolean validDuplicateUnFixMember(MemberDto member) {
        ArrayList<MemberDto> result = memberRepository.findByName(member.getName());
        for (MemberDto members : result) {
            if(members.getRole() == MemberRole.fixed || members.getRole() == MemberRole.admin){
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<MemberDto> findByName(String memberName) {
        return memberRepository.findByName(memberName);
    }
}
