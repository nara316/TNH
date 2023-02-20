package go.travel.dnh.security;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = userMapper.findById(username);

        String mem_id_DB = user.getUsername();
        System.out.println("mem_id_DB = " + mem_id_DB);
        String mem_pwd_DB = user.getPassword();
        System.out.println("mem_pwd_DB = " + mem_pwd_DB);
        System.out.println("user.getAuthorities() = " + user.getAuthorities());

            if (user == null) {
                throw new UsernameNotFoundException(username);
            } else {
                if(username.equals(mem_id_DB)) {
                    System.out.println("CustomUserDetailsService 들어옴~~");
                    return createUserDetails(user);
                } else {
                    return null;
                }
            }


    }
    private UserDetails createUserDetails(UserDetails user){
        System.out.println("createUserdetail드러옴");

        int mno = userMapper.findByMnoFromId(user.getUsername());
        System.out.println("mno = " + mno);
        System.out.println("user.getUsername() = " + user.getUsername());
        System.out.println("user.getPassword() = " + user.getPassword());
                //role -> mno로 찾기
        String role = userMapper.readAuthority(mno).toString();
        //roles는 권한이 ROLE_로 시작하는걸 읽어옴
        System.out.println("createUserDetailsrole = " + role);
        //ROLE이아니라 [ROLE]로 들어왔는데 상관이 없나?
        //여기서 존나해맴..인증할때 loadUserByUsername(String username) 실행시키는데 DB에서 꺼내온 User객체를 return해야함
//        LoginUser loginUser = new LoginUser();
//        loginUser.setMem_id(user.getUsername());
//        loginUser.setMem_pwd(user.getPassword());
//        loginUser.setAuthorities(userMapper.readAuthority(mno));
//        return loginUser;
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role)
                .build();
    }

}
