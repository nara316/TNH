package go.travel.dnh.mapper;

import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface MemberJoinMapper {

    void insertMember(MemberDTO memberDTO);

    void insertMemberRole(Integer mno);

    int readById(String mem_id);

    int readByPhone(Integer mem_phone);

    void updatePwd(Map map);

    void insertSnsMember(MemberDTO memberDTO);

}
