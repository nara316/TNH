package go.travel.dnh.controller;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.PagingResponse;
import go.travel.dnh.domain.air.SearchDTO;
import go.travel.dnh.service.AirProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirController {

    private final AirProductService airProductService;

    @GetMapping("/list")
    public String air_list(@ModelAttribute("sch") final SearchDTO sch, Model m) {
        PagingResponse<AirProductDTO> list = airProductService.getList(sch);
        m.addAttribute("air", list);
        return "air/list";
    }

    @PostMapping("/search")
    public String air_search(@ModelAttribute("sch") final SearchDTO sch, Model m){
        PagingResponse<AirProductDTO> list = airProductService.getSearchList(sch);
        m.addAttribute("air", list);
        System.out.println(sch.getFrom());
        return "air/list";
    }


}
