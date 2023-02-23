package go.travel.dnh.mapper;

import go.travel.dnh.domain.notice.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeBoardMapper {

    void save(NoticeDTO noticeDTO);

    List<NoticeDTO> findAll();

    NoticeDTO select(Integer nno);

    void delete(Integer nno);

    void update(Integer nno);
}
