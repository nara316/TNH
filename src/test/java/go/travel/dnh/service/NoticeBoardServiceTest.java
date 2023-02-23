package go.travel.dnh.service;

import go.travel.dnh.domain.notice.NoticeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoticeBoardServiceTest {

    @Autowired
    NoticeBoardService noticeBoardService;

//    @Test
//    void write() {
//        NoticeDTO noticeDTO = new NoticeDTO();
//        noticeDTO.setContent("가고십다");
//        noticeDTO.setTitle("집에");
//        for (int i = 1; i <200; i++) {
//            noticeBoardService.write(noticeDTO);
//        }
//    }
}