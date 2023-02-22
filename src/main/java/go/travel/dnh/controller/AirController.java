package go.travel.dnh.controller;

import go.travel.dnh.domain.air.*;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import go.travel.dnh.service.AirProductService;
import go.travel.dnh.validation.ReservationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirController {

    private final AirProductService airProductService;


    //항공권 전체 목록
    @GetMapping("/list")
    public String air_list_all(@ModelAttribute("sch") final SearchDTO sch, Model m) {
        PagingResponse<AirProductDTO> list = airProductService.getList(sch);
        m.addAttribute("air", list);
        return "air/list";
    }
    //항공권 검색 페이지
    @GetMapping("/search")
    public String air_search(@ModelAttribute("sch") final SearchDTO sch, Model m) {
        List<AirportDTO> airportList = airProductService.getListAirport();
        m.addAttribute("airport",airportList);
        return "air/search";
    }
    //항공권 검색
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

        //여기 너무 하드코딩인데 바꾸고 십어요
        String from = "";
        String to = "";
        for (int i = 0; i < airportList.size(); i++) {
            if(sch.getFrom().equals(airportList.get(i).getAp_code())){
                from = airportList.get(i).getAp_name();
            } else if(sch.getTo().equals(airportList.get(i).getAp_code())){
                to = airportList.get(i).getAp_name();
            }
        }
        m.addAttribute("fromAP",from);
        m.addAttribute("toAP",to);

        return "air/search-list";
    }

    // 예약정보 예약화면에 넘기기
    @PostMapping("/res-check")
    public String check(@ModelAttribute("resInfo") final ReservationInfo resInfo,Model m) {
        AirProductDTO outPro = airProductService.readRes(resInfo.getAir_from_check());
        AirProductDTO inPro = airProductService.readRes(resInfo.getAir_to_check());

        m.addAttribute("resInfo",resInfo);
        m.addAttribute("outPro",outPro);
        m.addAttribute("inPro", inPro);
        return "/air/reservation";
    }



    //예약정보 저장하기

    @PostMapping("/reservation")
    public String reservation(AirReservationDTO dto, @ModelAttribute("reservationDetails") ReservationDetail reservationDetails, Model m) {
        airProductService.reservation(dto,reservationDetails);
        return "air/pay";
    }


}
