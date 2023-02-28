package go.travel.dnh.domain.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialUser {
    private int sno;
    private String s_name;
    private String s_id;
    private String s_pwd;
}
