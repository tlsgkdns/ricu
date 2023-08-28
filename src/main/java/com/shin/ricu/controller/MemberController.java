package com.shin.ricu.controller;

import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping({"/info", "/edit"})
    public void userInfo(Model model, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO)
    {
        MemberDTO memberDTO = memberService.getMember(memberSecurityDTO.getMemberID());
        model.addAttribute("memberDTO", memberDTO);
    }

    @GetMapping("/register")
    public void getRegister(Model model, @RequestParam(required = false)String error)
    {
        model.addAttribute("error", error);
    }

    @PostMapping("/register")
    public String joinMember(RedirectAttributes redirectAttributes, MemberDTO memberDTO)
    {
        try
        {
           memberService.joinMember(memberDTO);
        } catch (MemberIDExistException e)
        {
            redirectAttributes.addFlashAttribute("error", "Already Exist ID");
           return "redirect:/member/register";
        }
        return "redirect:/gallery/home";
    }

    @GetMapping(value = {"/login", "/", ""})
    public void login(Model model, @RequestParam(required = false)String error, HttpServletRequest request)
    {
        request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
        model.addAttribute("error", error);
    }
    @PostMapping("/login")
    public void loginCheck()
    {

    }

    @PostMapping("/edit")
    public String editProfile(MemberDTO memberDTO, RedirectAttributes redirectAttributes)
    {
        log.info(memberDTO);
        memberService.editMember(memberDTO);
        redirectAttributes.addAttribute("memberDTO", memberDTO.getMemberID());
        return "redirect:/member/info";
    }
}
