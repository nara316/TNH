package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;

public interface MemberJoinRepository {

    //회원가입 메서드
    public void joinMember(MemberDTO memberDTO);

    public void insertMemberRole(Integer mno);

    public int findMember(String mem_id);

    public void updatePwd(String mem_id,String mem_pwd);

}
