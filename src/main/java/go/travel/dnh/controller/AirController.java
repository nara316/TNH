package go.travel.dnh.controller;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.domain.air.AirportDTO;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirController {

    private final AirProductService airProductService;


    @GetMapping("/list")
    public String air_list_all(@ModelAttribute("sch") final SearchDTO sch, Model m) {
        PagingResponse<AirProductDTO> list = airProductService.getList(sch);
        m.addAttribute("air", list);
        return "air/list";
    }
    @GetMapping("/search")
    public String air_search(@ModelAttribute("sch") final SearchDTO sch, Model m) {
        List<AirportDTO> airportList = airProductService.getListAirport();
        m.addAttribute("airport",airportList);
        return "air/search";
    }

    @PostMapping("/search")
    public String air_search(@ModelAttribute("sch") final SearchDTO sch, HttpServletResponse res, Model m) throws IOException {
        PagingResponse<AirProductDTO> listFrom = airProductService.getSearchFromList(sch);
        PagingResponse<AirProductDTO> listTo = airProductService.getSearchToList(sch);
        List<AirportDTO> airportList = airProductService.getListAirport();
        if(listFrom.getList().isEmpty() || listTo.getList().isEmpty()) {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('해당 항공편은 존재하지 않습니다. 다시 검색해주세요'); </script>");
            out.flush();
            m.addAttribute("airport",airportList);
            return "air/search";
        }
        m.addAttribute("airFrom", listFrom);
        m.addAttribute("airTo", listTo);
        m.addAttribute("airport",airportList);
        return "air/search-list";
    }
    @GetMapping("/search-list")
    public String air_list(@ModelAttribute("sch") final SearchDTO sch, Model m) {
        PagingResponse<AirProductDTO> listFrom = airProductService.getSearchFromList(sch);
        PagingResponse<AirProductDTO> listTo = airProductService.getSearchToList(sch);
        List<AirportDTO> airportList = airProductService.getListAirport();
        m.addAttribute("airFrom", listFrom);
        m.addAttribute("airTo", listTo);
        m.addAttribute("airport",airportList);
        System.out.println(airportList);
        return "redirect:/air/search-list";
    }


}
