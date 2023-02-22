package go.travel.dnh.repository;

import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberLoginRepository {
    //로그인
    MemberDTO findByMember(MemberDTO memberDTO);
    MemberDTO findById(String mem_id);

    void update(MemberDTO memberDTO);
}
