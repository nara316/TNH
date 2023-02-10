package go.travel.dnh.service;

import go.travel.dnh.domain.User.User;
import go.travel.dnh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    @Override
    public Collection<GrantedAuthority> getAuthorities(int mno) {
        List<String> string_authorities = userMapper.readAuthority(mno);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : string_authorities) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        int mno = userMapper.findByMnoFromId(username);
        User user = userMapper.readUser(mno);
        user.setAuthorities(getAuthorities(mno));
        return user;
    }

}
