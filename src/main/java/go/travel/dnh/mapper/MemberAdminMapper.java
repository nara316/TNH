package go.travel.dnh.mapper;

import go.travel.dnh.domain.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberAdminMapper {

    //회원 전체목록 조회
    public List<MemberDTO> getMemberList();

    //회원 삭제
    public void deleteMember(Integer mno);
}
