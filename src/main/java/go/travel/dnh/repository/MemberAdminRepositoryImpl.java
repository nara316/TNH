package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.mapper.MemberAdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberAdminRepositoryImpl implements MemberAdminRepository{

    private final MemberAdminMapper memberAdminMapper;

    @Override
    public List<MemberDTO> getMemberList() {
        return memberAdminMapper.getMemberList();
    }

    @Override
    public void deleteMember(Integer mno){
        memberAdminMapper.deleteMember(mno);
    }

    @Override
    public void deleteMemberRole(Integer mno){
        memberAdminMapper.deleteMemberRole(mno);
    }
}
