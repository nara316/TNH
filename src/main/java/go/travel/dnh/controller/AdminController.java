package go.travel.dnh.controller;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.service.AirProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AirProductService airProductService;

    @GetMapping("/main")
    public String adminHome(){
        return "admin/main";
    }


    @GetMapping("/airlist")
    public String adminAirList(Model m){
        List<AirProductDTO> list = airProductService.getListAdmin();
        m.addAttribute("list",list);
        return "admin/air/list";
    }

    @GetMapping("/airWrite")
    public String airWritePage(){
        return "admin/air/write";
    }

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
}
