package go.travel.dnh.domain.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialUser {
    private String id;
    private String name;
    private String email;
    private String password;
}
