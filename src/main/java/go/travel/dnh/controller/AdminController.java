package go.travel.dnh.controller;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.service.AirProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AirProductService airProductService;

//    admin 메인 페이지 이동
    @GetMapping("/main")
    public String adminHome(){
        return "admin/main";
    }

//    항공권 리스트 페이지
    @GetMapping("/airlist")
    public String adminAirList(Model m){
        List<AirProductDTO> list = airProductService.getListAdmin();
        m.addAttribute("list",list);
        return "admin/air/list";
    }

//    항공권 등록페이지
    @GetMapping("/airWrite")
    public String airWritePage(){
        return "admin/air/write";
    }

//    항공권 등록하기 (admin 계정 생성 후 in_user/up_user 수정)
    @PostMapping("/airWrite")
    public String airWrite(AirProductDTO dto, Model m){
        String writer = "admin";
        dto.setIn_user(writer);
        dto.setUp_user(writer);
        dto.setAr_to_date(dto.getAr_from_date().plusMinutes(dto.getAr_time()));
        dto.setAr_res_cnt(dto.getAr_cnt());
        m.addAttribute(dto);
        airProductService.write(dto);
        System.out.println(dto);
        return "redirect:/admin/airlist";
    }

    @GetMapping("/airModify/{ano}")
    public String air_modify_page(@PathVariable("ano")Integer ano, Model m) {
        AirProductDTO dto = airProductService.read(ano);
        m.addAttribute("list",dto);
        System.out.println(dto);
        return "admin/air/modify";
    }
    @PostMapping("/airModify")
    public String air_modify(AirProductDTO dto) {
        String writer = "admin2";
        dto.setUp_user(writer);
        airProductService.modify(dto);
        return "redirect:/admin/airlist";
    }

    @GetMapping("/airRemove")
    public String remove(@RequestParam("ano") Integer ano) {
        airProductService.remove(ano);
        return "redirect:/admin/airlist";
    }
}
