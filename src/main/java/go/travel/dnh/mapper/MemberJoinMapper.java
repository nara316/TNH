package go.travel.dnh.mapper;

import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface MemberJoinMapper {

    void insertMember(MemberDTO memberDTO);

    int findMember(String mem_id);

    void updatePwd(Map map);
}
