package go.travel.dnh.service;

import go.travel.dnh.validation.MemberjoinForm;

public interface MemberJoinService {

    void joinMember(MemberjoinForm form);

    int findMember(String mem_id);

    int findMemberbyPhone(int mem_phone);

    String randomNum();

    int mailNumCheck(String checkInput);

    void Sendmail(String mem_id);

    String sendPwdMail(String mem_id);

    void joinSnsMember(MemberjoinForm form);

}
