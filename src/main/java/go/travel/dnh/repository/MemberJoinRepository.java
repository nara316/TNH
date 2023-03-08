package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;

public interface MemberJoinRepository {

    //회원가입 메서드
    void joinMember(MemberDTO memberDTO);

    void joinSnsMember(MemberDTO memberDTO);
    void insertMemberRole(Integer mno);

    int readById(String mem_id);

    int readByPhone(Integer mem_pwd);

    void updatePwd(String mem_id,String mem_pwd);

}
