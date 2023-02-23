package go.travel.dnh.repository;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.mapper.MemberJoinMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberJoinRepositoryImpl implements MemberJoinRepository {

    private final MemberJoinMapper memberJoinMapper;

    @Override
    public void joinMember(MemberDTO memberDTO) {
        memberJoinMapper.insertMember(memberDTO);
    }

    @Override
    public void joinSnsMember(MemberDTO memberDTO) {
        memberJoinMapper.insertSnsMember(memberDTO);
    }

    @Override
    public void insertMemberRole(Integer mno){
        memberJoinMapper.insertMemberRole(mno);
    }

    @Override
    public int readById(String mem_id){
        return memberJoinMapper.readById(mem_id);
    }

    @Override
    public int readByPhone(Integer mem_pwd){
        return memberJoinMapper.readByPhone(mem_pwd);
    }

    @Override
    public void updatePwd(String mem_id,String mem_pwd){
        Map map = new HashMap();
        map.put("mem_id", mem_id);
        map.put("mem_pwd",mem_pwd);
        memberJoinMapper.updatePwd(map);
    }
}
