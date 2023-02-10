package go.travel.dnh.mapper;

import go.travel.dnh.domain.User.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public User readUser(int mno);

    public List<String> readAuthority(int mno);

    public int findByMnoFromId(String mem_id);
}
