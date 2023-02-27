package go.travel.dnh.repository;

import go.travel.dnh.domain.User.WithdrawalForm;
import go.travel.dnh.domain.User.updateForm;
import go.travel.dnh.domain.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberLoginRepositoryImpl implements MemberLoginRepository {
    private final MemberLoginRepository memberLoginRepository;

    @Override
    public MemberDTO findByMember(MemberDTO memberDTO) {
        return memberLoginRepository.findByMember(memberDTO);
    }

    @Override
    public MemberDTO findById(String mem_id) {
        return memberLoginRepository.findById(mem_id);
    }

    @Override
    public void update(MemberDTO memberDTO) {
    }

    @Override
    public void delete(int mno) {
    }

    @Override
    public void andInsert(WithdrawalForm withdrawalForm) {

    }
}
