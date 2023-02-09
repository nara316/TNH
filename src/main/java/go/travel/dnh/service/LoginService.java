package go.travel.dnh.service;

import go.travel.dnh.domain.RequestUser;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.repository.MemberLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    MemberLoginRepository memberLoginRepository;

    public void MemberLogin(RequestUser requestUser) {
//        String result = memberLoginRepository.findByMember(RequestUser);
//        if(result.equals("success")) {
//
//        }

    }
}
