package go.travel.dnh.service;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.validation.MemberjoinForm;

public interface JoinService {

    public void joinMember(MemberjoinForm form);

    public int findMember(String mem_id);

}
