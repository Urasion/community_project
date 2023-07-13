package community_project.main.service;

import community_project.main.controller.Form.JoinForm;
import community_project.main.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface MemberService {
    boolean join (JoinForm joinForm);

    MemberDto login(String id, String password);
    ArrayList<MemberDto> findByName(String memberName);
}
