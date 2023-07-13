package community_project.main.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉트 실행 {}",requestURI);
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("loginId") == null){
            log.info("Session : {}    LoginId : {}",session, session.getAttribute("loginId"));
            log.info("미인증 사용자 접근");
            response.sendRedirect("/login?redirectURL="+requestURI);
            return false;
        }
        return true;
    }
}
