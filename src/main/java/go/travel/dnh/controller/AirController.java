package go.travel.dnh.controller;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.air.*;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import go.travel.dnh.service.AirProductService;
import go.travel.dnh.service.MemberLoginService;
import go.travel.dnh.service.ReservationService;
import go.travel.dnh.validation.ReservationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final MemberLoginService memberLoginService;
    private final ReservationService reservationService;


    //항공권 전체 목록
    @GetMapping("/list")
    public String air_list_all(@ModelAttribute("sch") final SearchDTO sch, Model m) {
        PagingResponse<AirProductDTO> list = airProductService.getList(sch);
        m.addAttribute("air", list);
        return "air/list";
    }

    @GetMapping("/region")
    public String region(@ModelAttribute("sch") final SearchDTO sch, Model m){
        PagingResponse<AirProductDTO> listRegion = airProductService.indexSearch(sch);
        List<AirportDTO> airportList = airProductService.getListAirport();
        m.addAttribute("airOneWay", listRegion);
        m.addAttribute("airport",airportList);

        System.out.println(sch.getOneTo());
        System.out.println(listRegion.getList());


        return "/air/search-list-oneway";
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
        PagingResponse<AirProductDTO> listOneWay = airProductService.getSearchOneWayList(sch);
        PagingResponse<AirProductDTO> listFrom = airProductService.getSearchFromList(sch);
        PagingResponse<AirProductDTO> listTo = airProductService.getSearchToList(sch);
        List<AirportDTO> airportList = airProductService.getListAirport();

        m.addAttribute("airport",airportList);
        
        //편도
        if(sch.getRoundFrom()==null){
            if(listOneWay.getList().isEmpty()){
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>alert('해당 항공편은 존재하지 않습니다. 다시 검색해주세요'); </script>");
                out.flush();
                return "air/search";
            }

            m.addAttribute("airOneWay", listOneWay);

            String from = "";
            String to = "";
            for (int i = 0; i < airportList.size(); i++) {
                if(sch.getOneFrom().equals(airportList.get(i).getAp_code())){
                    from = airportList.get(i).getAp_name();
                } else if(sch.getOneTo().equals(airportList.get(i).getAp_code())){
                    to = airportList.get(i).getAp_name();
                }
            }

            m.addAttribute("fromAP",from);
            m.addAttribute("toAP",to);
            return "/air/search-list-oneway";

        }
        //왕복
        else if (sch.getOneFrom()==null) {
            if(listFrom.getList().isEmpty() || listTo.getList().isEmpty()){
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>alert('해당 항공편은 존재하지 않습니다. 다시 검색해주세요'); </script>");
                out.flush();
                m.addAttribute("airport",airportList);
                return "air/search";
            }


            m.addAttribute("airFrom", listFrom);
            m.addAttribute("airTo", listTo);

            String from = "";
            String to = "";
            for (int i = 0; i < airportList.size(); i++) {
                if(sch.getRoundFrom().equals(airportList.get(i).getAp_code())){
                    from = airportList.get(i).getAp_name();
                } else if(sch.getRoundTo().equals(airportList.get(i).getAp_code())){
                    to = airportList.get(i).getAp_name();
                }
            }
            m.addAttribute("fromAP",from);
            m.addAttribute("toAP",to);
            return "/air/search-list-round";
        }
        return "air/search";
    }


    @PostMapping("/sort-oneway")
    public String sortListOneWay(@RequestParam("sortValue") String sortValue,
                                  @ModelAttribute("sch") final SearchDTO sch,
                                  Model m) {
        List<AirportDTO> airportList
                = airProductService.getListAirport();


        m.addAttribute("airport", airportList);

        String from = "";
        String to = "";
        for (int i = 0; i < airportList.size(); i++) {
            if(sch.getOneFrom().equals(airportList.get(i).getAp_code())){
                from = airportList.get(i).getAp_name();
            } else if(sch.getOneTo().equals(airportList.get(i).getAp_code())){
                to = airportList.get(i).getAp_name();
            }
        }

        m.addAttribute("fromAP",from);
        m.addAttribute("toAP",to);

        PagingResponse<AirProductDTO> sortOneWay
                = airProductService.OneWaySort(sch);
        sch.setSortValue(sortValue);
        m.addAttribute("airOneWay", sortOneWay);
        return "/air/search-list-oneway";
    }

    @PostMapping("/sort-round")
    public String sortListOneWayP(@RequestParam("sortValue") String sortValue,
                                  @ModelAttribute("sch") final SearchDTO sch,
                                  Model m) {

        System.out.println("동작하니?");
        System.out.println(sch.getSortValue());
        List<AirportDTO> airportList = airProductService.getListAirport();

        m.addAttribute("airport", airportList);

        String from = "";
        String to = "";
        for (int i = 0; i < airportList.size(); i++) {
            if(sch.getRoundFrom().equals(airportList.get(i).getAp_code())){
                from = airportList.get(i).getAp_name();
            } else if(sch.getRoundTo().equals(airportList.get(i).getAp_code())){
                to = airportList.get(i).getAp_name();
            }
        }
        m.addAttribute("fromAP",from);
        m.addAttribute("toAP",to);

        PagingResponse<AirProductDTO> sortRoundOut = airProductService.roundSortOut(sch);
        PagingResponse<AirProductDTO> sortRoundIN = airProductService.roundSortIn(sch);
        m.addAttribute("airFrom", sortRoundOut);
        m.addAttribute("airTo", sortRoundIN);

        System.out.println(sortRoundOut.getList());
        System.out.println(sortRoundIN.getList());

        return "/air/search-list-round";
    }



    @GetMapping("/search-list-oneway")
    public String onewaySchList(@ModelAttribute("sch") final SearchDTO sch,Model m){
        PagingResponse<AirProductDTO> listOneWay = airProductService.getSearchOneWayList(sch);
        List<AirportDTO> airportList = airProductService.getListAirport();


        m.addAttribute("airOneWay", listOneWay);
        m.addAttribute("airport",airportList);

        return "air/search-list-oneway";
    }

    @GetMapping("/search-list-round")
    public String roundSchList(@ModelAttribute("sch") final SearchDTO sch,Model m){

        PagingResponse<AirProductDTO> listFrom = airProductService.getSearchFromList(sch);
        PagingResponse<AirProductDTO> listTo = airProductService.getSearchToList(sch);
        List<AirportDTO> airportList = airProductService.getListAirport();

        m.addAttribute("airFrom", listFrom);
        m.addAttribute("airTo", listTo);
        m.addAttribute("airport",airportList);
        return "air/search-list-round";
    }


    // 예약정보 예약화면에 넘기기
    @PostMapping("/res-check")
    public String check(@ModelAttribute("resInfo") final ReservationInfo resInfo,Model m) {
        AirProductDTO outPro = airProductService.readRes(resInfo.getAir_from_check());
        AirProductDTO inPro = airProductService.readRes(resInfo.getAir_to_check());
        AirProductDTO onewayPro = airProductService.readRes(resInfo.getAir_oneway_check());

        System.out.println("ea "+resInfo.getEa());
        System.out.println("to "+resInfo.getAir_to());
        System.out.println("from "+resInfo.getAir_from());
        System.out.println("ano "+resInfo.getAir_oneway_check());

        if(resInfo.getAir_oneway_check()!=null){
            m.addAttribute("resInfo",resInfo);
            m.addAttribute("onewayPro",onewayPro);
            return "air/res-oneway";
        } else {

            System.out.println(resInfo.getEa());
            m.addAttribute("resInfo", resInfo);
            m.addAttribute("outPro", outPro);
            m.addAttribute("inPro", inPro);
            return "air/res-round";
        }
    }



    //예약정보 저장하기
//    @PostMapping("/reservation")
//    public String reservation(AirReservationDTO dto, @ModelAttribute("reservationDetails") ReservationDetail reservationDetails, Model m, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication) {
//        airProductService.reservation(dto,reservationDetails,loginUser,authentication);
//        AirProductDTO outDTO = airProductService.readRes(dto.getOut_ano());
//        AirProductDTO inDTO = airProductService.readRes(dto.getIn_ano());
//        AirReservationDTO resDTO = dto;
//
//
//
//        m.addAttribute("resDTO", resDTO);
//        m.addAttribute("outDTO", outDTO);
//        m.addAttribute("inDTO", inDTO);
//
//        if(inDTO==null){
//            return "air/order-oneway";}
//        else
//            return "air/order-round";
//    }

    /*예약DB 저장*/
    @PostMapping("/reservation")
    public String reservation(AirReservationDTO dto, @ModelAttribute("reservationDetails") ReservationDetail reservationDetails, Model m, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication) {
        airProductService.reservation(dto,reservationDetails,loginUser,authentication);

        MemberDTO memberDTO = memberLoginService.findMember(loginUser,authentication);
        List<AirReservationListDTO> revList = reservationService.selectMyRes(loginUser,authentication);

        m.addAttribute("memberDTO", memberDTO);
        m.addAttribute("revList", revList);
        return "order/bookingList";
    }



}
