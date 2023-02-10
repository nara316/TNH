package go.travel.dnh.mapper;

import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberJoinMapper {

    void insertMember(MemberDTO memberDTO);

    int findMember(String mem_id);
}
