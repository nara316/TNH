package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;

import java.util.List;

public interface MemberAdminRepository {

    //회원정보 조회
    public List<MemberDTO> getMemberList();

    public void deleteMember(Integer mno);
}
