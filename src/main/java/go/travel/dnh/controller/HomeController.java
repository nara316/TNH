package go.travel.dnh.controller;

import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.domain.notice.NoticePageResponse;
import go.travel.dnh.domain.notice.NoticeSearchDTO;
import go.travel.dnh.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final NoticeBoardService noticeBoardService;

    @GetMapping
    public String index(@ModelAttribute("params") NoticeSearchDTO params, Model model){
        NoticePageResponse<NoticeDTO> response = noticeBoardService.boardList(params);
        model.addAttribute("response",response );
        return "index";
    }
}
