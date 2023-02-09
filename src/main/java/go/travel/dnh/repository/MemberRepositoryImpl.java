package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.member.MemberDTO;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberLoginRepository{

    @Override
    public String findByMember(MemberDTO memberDAO) {
        MemberDTO LoginUser = memberDAO;
        if(LoginUser==null) {
            return "success";
            //DB에 값이 없을때
        }
        //DB에 값이 있을때
        return "false";
    }
}
