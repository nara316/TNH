package go.travel.dnh.controller;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.service.AirProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
