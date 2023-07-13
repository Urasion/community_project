package community_project.main.controller;

import community_project.main.controller.Form.JoinForm;
import community_project.main.controller.Form.LoginForm;
import community_project.main.dto.MemberDto;
import community_project.main.service.MemberService;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login_phase1(@ModelAttribute("loginForm") LoginForm form, HttpServletRequest httpServletRequest, @RequestParam(value = "redirectURL") String redirectURL,
                               Model model){
        log.info("GET loginId : {}  password: {}",form.getLoginId(), form.getPassword());
        HttpSession session = httpServletRequest.getSession(false);
        if(session != null && session.getAttribute("loginId") != null){
            log.info("로그인 정보가 있습니다!!!!!");
           return "redirect:" + redirectURL;
        }
        model.addAttribute("redirectURL", redirectURL);
        return "member/loginForm";
    }
    @PostMapping("/login")
    public String login_phase2(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, HttpServletRequest httpServletRequest
    ,@RequestParam(value = "redirectURL") String redirectURL)  {
        log.info("POST loginId : {}  password: {}",form.getLoginId(), form.getPassword());

        if(bindingResult.hasErrors()){
            return "member/loginForm";
        }
        MemberDto member = memberService.login(form.getLoginId(), form.getPassword());
        if(member == null){
            bindingResult.reject("loginFail","아이디 비밀번호가 일치하지 않습니다.");
            return "member/loginForm";
        }
        log.info("memberId : {}, memberName : {}, memberRole :{}, memberCreate : {}",member.getId(), member.getName(), member.getRole(), member.getJoinAt());
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("loginId",member.getId());
        session.setAttribute("loginName",member.getName());
        session.setAttribute("loginRole",member.getRole());
        return "redirect:" + redirectURL;
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest, @RequestParam(value = "redirectURL", required = false) String redirectURL){
        log.info("로그아웃 페이즈 접근");
        HttpSession session = httpServletRequest.getSession(false);
        if(session != null){
            session.invalidate(); //세션의 모든 속성을 삭제
        }
        return "redirect:" + redirectURL;
    }
    @GetMapping("/member/join")
    public String joinMemberPage(@ModelAttribute("joinForm")JoinForm joinForm, @RequestParam("redirectURL") String redirectURL, HttpServletRequest httpServletRequest,
                                 Model model){
        HttpSession session = httpServletRequest.getSession(false);
        if(session != null && session.getAttribute("loginId") != null){
            return "redirect:" + redirectURL;
        }
        model.addAttribute("redirectURL", redirectURL);
        return "member/joinForm";
    }
    @PostMapping("/member/join")
    public String joinMember(@Valid @ModelAttribute("joinForm") JoinForm joinForm, BindingResult bindingResult, @RequestParam("redirectURL") String redirectURL){
        if(bindingResult.hasErrors()){
            log.info("체크박스 체크 : {}",joinForm.getFixCheck());
            return "member/joinForm";
        }
        log.info("체크박스 체크 : {}",joinForm.getFixCheck());
        if(!memberService.join(joinForm)){
           bindingResult.reject("joinFail","중복되는 아이디 혹은 고닉인 경우 닉네임이 중복을 확인해주세요");
            return "member/joinForm";
        }
        return "redirect:" + redirectURL;

    }

}
