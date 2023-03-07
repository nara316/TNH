package go.travel.dnh.service;

import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.domain.notice.NoticePageResponse;
import go.travel.dnh.domain.notice.NoticeSearchDTO;
import org.springframework.web.multipart.MultipartFile;

public interface NoticeBoardService {
    void write(NoticeDTO noticeDTO, MultipartFile file) throws Exception;

    NoticePageResponse<NoticeDTO> boardList(NoticeSearchDTO noticeSearchDTO);

    NoticeDTO boardView(Integer nno);

    void boardDelete(Integer nno);

    void modifyBoard(NoticeDTO noticeDTO, MultipartFile file) throws Exception;
}
