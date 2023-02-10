package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class MemberLoginRepositoryImpl implements MemberLoginRepository {

    @Override
    public MemberDTO findByMember(MemberDTO memberDTO) {
        if(memberDTO!=null) {
            return memberDTO;
        }
        return null;
    }

    @Override
    public MemberDTO findById(MemberDTO memberDTO) {
        if(memberDTO!=null) {
            return memberDTO;
        }
        return null;
    }
}
