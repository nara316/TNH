package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberLoginRepository {
    //로그인
    public MemberDTO findByMember(MemberDTO memberDTO);
    public MemberDTO findById(String mem_id);

}
