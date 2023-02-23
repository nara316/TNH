package go.travel.dnh.social;

import go.travel.dnh.domain.User.SocialUser;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    public static OAuthAttributes of(String registrationId, String usernameAttributesName, Map<String,Object> attributes){
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } else if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(usernameAttributesName, attributes);
    }

    private static OAuthAttributes ofGoogle(String usernameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }
    private static OAuthAttributes ofNaver(String usernameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }
    private static OAuthAttributes ofKakao(String usernameAttributeName, Map<String, Object> attributes) {
        System.out.println("usernameAttributeName = " + usernameAttributeName);
        System.out.println("카카오로그인..");
        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> accountName = (Map<String, Object>) response.get("profile");
        String accountEmail = response.get("email").toString();

        return OAuthAttributes.builder()
                .name((String) accountName.get("nickname"))
                .email((String) response.get("email"))
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }
    public SocialUser toEntity(){
        return SocialUser.builder()
                .s_name(name)
                .s_id(email)
                .build();
    }
}
