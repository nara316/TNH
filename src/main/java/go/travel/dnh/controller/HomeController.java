package go.travel.dnh.controller;

import go.travel.dnh.domain.air.AirportDTO;
import go.travel.dnh.domain.air.SearchDTO;
import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.domain.notice.NoticePageResponse;
import go.travel.dnh.domain.notice.NoticeSearchDTO;
import go.travel.dnh.service.AirProductService;
import go.travel.dnh.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final NoticeBoardService noticeBoardService;
    private final AirProductService airProductService;

    @GetMapping
    public String index(@ModelAttribute("params") NoticeSearchDTO params,@ModelAttribute("sch") final SearchDTO sch, Model model){
        NoticePageResponse<NoticeDTO> response = noticeBoardService.boardList(params);
        List<AirportDTO> airportList = airProductService.getListAirport();
        model.addAttribute("airport",airportList);
        model.addAttribute("response",response );
        return "index";
    }

}
