package go.travel.dnh.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Random;

@Getter
public class SessionUser implements Serializable {
    //인증된 사용자 정보를 저장하기 위한 DTO, 세션안에 넣을거라서 직렬화 해줌
    private String name;
    private String email;

    public SessionUser(SocialUser user) {
        this.name = user.getS_name();
        this.email = user.getS_id();
    }
}
