package go.travel.dnh.security;

import go.travel.dnh.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    public ResponseEntity<TokenInfo> login(String mem_id, String mem_pwd) {
        //받은 id pwd를 기반으로 Authentication 객체생성하는 메서드 -> 인증여부를 확인하는 값이 false
        if(userMapper.findById(mem_id)==null) {
            //로그인 유저가 존재하지않을때 그냥 빈 토큰객체 반환
            TokenInfo nullUser = new TokenInfo(null,null,null);
            return ResponseEntity.ok(nullUser);
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mem_id, mem_pwd);
            //이 토큰은 아이디 패스워드를 가진 인증 전 객체
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            //Authentication 객체를 생성해(시큐리티 제공객체)주면 알아서 비밀번호 암호화한걸 비교해줌 ->성공이면 인증여부가 true로 바뀜
            //이때 UserDetailService를 상속한 CustomUserDetailService가 실행됨
            TokenInfo token = jwtTokenProvider.generateToken(authentication);
            //인증여부가 true인걸로 토큰생성
            System.out.println("token = " + token);
            System.out.println("컨트롤러 로그인 끝");
            return ResponseEntity.ok(token);
        }
    }
    public String getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information.");
        }
        return authentication.getName();
    }
}
