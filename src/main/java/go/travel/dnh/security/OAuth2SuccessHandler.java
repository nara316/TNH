package go.travel.dnh.security;

import go.travel.dnh.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final HttpSession httpSession;
    private final UserMapper userMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //소셜 로그인 후 기존 DB에 회원가입 되어있으면 있으면 index
        String email = httpSession.getAttribute("socialEmail").toString();
        if (userMapper.findById(email) != null) {
            UserDetails user = userMapper.findById(email);
            response.sendRedirect("http://localhost:8080");
        } else {
            // 아닐시엔 회원수정으로 가서 기존 DB에 회원가입 할 수 있도록 update
            response.sendRedirect("http://localhost:8080/login/oauth/update/" + email);
        }
    }
}


