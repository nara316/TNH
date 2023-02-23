package go.travel.dnh.controller;


import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.mapper.NoticeBoardMapper;
import go.travel.dnh.service.NoticeBoardService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeBoardService noticeBoardService;

    @GetMapping("/list")
    public String noticeList(Model model) {
        model.addAttribute("list", noticeBoardService.boardList());
        return "admin/notice/list";
    }
    //공지 글 상세
    @GetMapping("/list/detail")
    public String noticeView(Model model, Integer nno) {
        model.addAttribute("board", noticeBoardService.boardView(nno));
        return "admin/notice/listdetail";
    }

    @GetMapping("/write")
    public String noticeWrite() {
        return "admin/notice/write";
    }
    //공지 글 등록
    @PostMapping("/write")
    public String Write(NoticeDTO noticeDTO, Model model, MultipartFile file) throws Exception {
        noticeBoardService.write(noticeDTO, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchURL", "/notice/list");
        return "message";
    }

    @GetMapping("/list/delete")
    public String boardDelete(Integer nno) {
        noticeBoardService.boardDelete(nno);
        return "redirect:/notice/list";
    }
    @GetMapping("/list/modify/{nno}")
    public String boardModify(@PathVariable("nno") Integer nno,Model model) {
        model.addAttribute("board", noticeBoardService.boardView(nno));
        return "admin/notice/modify";
    }

    @PostMapping("/list/update/{nno}")
    public String boardUpdate(@PathVariable("nno") Integer nno,NoticeDTO noticeDTO, MultipartFile file) throws Exception {
        NoticeDTO noticeTemp = noticeBoardService.boardView(nno);
        noticeTemp.setN_title(noticeDTO.getN_title());
        noticeTemp.setN_content(noticeDTO.getN_content());
        noticeBoardService.write(noticeDTO, file);

        return "redirect:/notice/list";
    }
}
