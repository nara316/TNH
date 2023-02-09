package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDAO;
import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MemberLoginRepository {
    //로그인
    public String findByMember(MemberDTO memberDAO);

}
