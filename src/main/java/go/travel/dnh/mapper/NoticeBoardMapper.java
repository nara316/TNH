package go.travel.dnh.mapper;

import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.domain.notice.NoticeSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeBoardMapper {

    void save(NoticeDTO noticeDTO);

    List<NoticeDTO> findAll(NoticeSearchDTO noticeSearchDTO);

    int count(NoticeSearchDTO noticeSearchDTO);
    NoticeDTO select(Integer nno);

    void delete(Integer nno);

    void update(NoticeDTO noticeDTO);

    List<NoticeDTO> search(NoticeSearchDTO noticeSearchDTO);
}
