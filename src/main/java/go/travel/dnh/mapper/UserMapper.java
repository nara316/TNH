package go.travel.dnh.mapper;

import go.travel.dnh.domain.User.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Mapper
public interface UserMapper {
     LoginUser readUser(int mno);

     List<GrantedAuthority> readAuthority(int mno);

    int findByMnoFromId(String mem_id);

    LoginUser findById(String mem_id);

    boolean existByUsername(String mem_id);
}
