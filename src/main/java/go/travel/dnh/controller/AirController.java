package go.travel.dnh.controller;

import go.travel.dnh.domain.air.AirProductDTO;
import go.travel.dnh.service.AirProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirController {

    private final AirProductService airProductService;

    @GetMapping("/list")
    public String air_list(AirProductDTO dto, Model m) {
        List<AirProductDTO> list = airProductService.getList();
        m.addAttribute("list", list);
        return "air/list";
    }

}
