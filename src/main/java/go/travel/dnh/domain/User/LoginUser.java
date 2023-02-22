package go.travel.dnh.domain.User;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails, OAuth2User{

    private Integer mno;
    private String mem_id;
    private String mem_pwd;
    private String mem_name;
    private String mem_phone;
    private String mem_gender;
    private Date mem_birth;

    private Map<String,Object> attributes;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return authorities;
    }

    @Override
    public String getPassword() {
        return mem_pwd;
    }

    @Override
    public String getUsername() {
        return mem_id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


    @Override
    public String getName() {
        return null;
    }
}
