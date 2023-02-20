package go.travel.dnh.service;

import go.travel.dnh.domain.User.SessionUser;
import go.travel.dnh.domain.User.SocialUser;
import go.travel.dnh.mapper.SocialUserMapper;
import go.travel.dnh.mapper.UserMapper;
import go.travel.dnh.social.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final SocialUserMapper socialUserMapper;
    private final UserMapper userMapper;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String email = String.valueOf(oAuth2User.getAttributes().get("email"));

        //소셜 로그인 종류 kakao,naver,google
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //소셜로그인한 사용자의 정보가 담긴 객체 생성
        OAuthAttributes attributes = OAuthAttributes.of(
                registrationId,
                userNameAttributeName,
                oAuth2User.getAttributes());

        SocialUser user = saveOrUpdate(attributes);
        SessionUser sessionUser = new SessionUser(user);
        String socialName = sessionUser.getName();
        String socialEmail = sessionUser.getEmail();

        Random random = new Random();		//랜덤 함수 선언
        int createNum = 0;  			//1자리 난수
        String ranNum = ""; 			//1자리 난수 형변환 변수
        int letter    = 6;			//난수 자릿수:6
        String resultNum = "";  		//결과 난수

        for (int i=0; i<letter; i++) {

            createNum = random.nextInt(9);		//0부터 9까지 올 수 있는 1자리 난수 생성
            ranNum =  Integer.toString(createNum);  //1자리 난수를 String으로 형변환
            resultNum += ranNum;
        }
        httpSession.setAttribute("socialName", socialName);
        httpSession.setAttribute("socialEmail", socialEmail);
        httpSession.setAttribute("socialPwd", resultNum);


        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private SocialUser saveOrUpdate(OAuthAttributes attributes) {
        //가입이 되어도 시큐리티 UserDetail 객체가 아니라서 권한이 의미없음
        //추후 최초 로그인시 바로 회원정보 수정으로 넘어가서 회원정보 수정-시큐리티 객체로 회원 저장
        SocialUser socialUser;
        //기존DB와 소셜로그인 DB에서 중복된 아이디가 없을때
            if (socialUserMapper.findByEmail(attributes.getEmail()) != null) {
                //소셜 DB에서 id를 찾았을 때 null이 아니면 ok
                socialUser = socialUserMapper.findByEmail(attributes.getEmail());
            } else {
                //null일 경우 소셜DB에 저장하고 바로 회원수정으로 넘어가서 기존 DB회원가입으로 넘어가기
                socialUser = attributes.toEntity();
                socialUserMapper.saveSocialUser(socialUser);
                socialUser = socialUserMapper.findByEmail(attributes.getEmail());
            }
            return socialUser;
    }
}
