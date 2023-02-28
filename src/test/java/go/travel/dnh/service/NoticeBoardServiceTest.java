package go.travel.dnh.service;

import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.domain.notice.NoticeSearchDTO;
import go.travel.dnh.mapper.NoticeBoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoticeBoardServiceTest {

    @Autowired
    NoticeBoardService noticeBoardService;
    @Autowired
    NoticeBoardMapper noticeBoardMapper;

    @Test
    void search() {
        String keyword = "볼이";
        NoticeSearchDTO noticeSearchDTO = new NoticeSearchDTO();
        noticeSearchDTO.setKeyword(keyword);
        List<NoticeDTO> list = noticeBoardMapper.search(noticeSearchDTO);
        list.get(1).getN_title();
        System.out.println("list.get(1).getN_title() = " + list.get(1).getN_title());
    }

}