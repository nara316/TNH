package go.travel.dnh.controller;

import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.service.MemberAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class MemberAdaminController {

    private final MemberAdminService memberAdminService;

    @GetMapping("/memberList")
    public String getMemberList(Model model){
        List<MemberDTO> memberList = memberAdminService.getMemberList();

        model.addAttribute("memberList", memberList);

        return "admin/member/list";
    }

    @GetMapping("/memberList/{mno}")
    public String remove(@PathVariable("mno") Integer mno, Model model) {

        memberAdminService.deleteMember(mno);

        return "redirect:/admin/memberList";
    }


}
