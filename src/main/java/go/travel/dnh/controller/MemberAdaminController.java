package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.MemberAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberAdaminController {

    private final MemberAdminService memberAdminService;

    @GetMapping("/memberList")
    public String getMemberList(Model model){
        List<MemberDTO> memberList = memberAdminService.getMemberList();

        //model.addAttribute("title", "회원목록조회");
        model.addAttribute("memberList", memberList);

        return "admin/member/list";
    }
}
