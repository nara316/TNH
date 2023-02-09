package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.mapper.MemberJoinMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJoinRepositoryImpl implements MemberJoinRepository {

    private final MemberJoinMapper memberJoinMapper;

    @Override
    public void joinMember(MemberDTO memberDTO) {
        memberJoinMapper.insertMember(memberDTO);
    }
}
