package com.shin.ricu.controller;

import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.exception.MemberIDIsNotExistException;
import com.shin.ricu.exception.MemberNicknameIsNotExistException;
import com.shin.ricu.security.dto.MemberSecurityDTO;
import com.shin.ricu.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping( "/info")
    public String userInfo(Model model, String id, String nickname, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO,
            HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(id == null)
        {
            if(nickname == null && memberSecurityDTO != null) id = memberSecurityDTO.getMemberID();
            else
            {
                try
                {
                    MemberDTO memberDTO = memberService.getMemberByNickname(nickname);
                    model.addAttribute("memberDTO", memberDTO);
                    return request.getRequestURI();
                } catch (MemberNicknameIsNotExistException e)
                {
                    return setError(redirectAttributes, "Member is Not Exist");
                }
            }
        }
        try {
            MemberDTO memberDTO = memberService.getMemberByID(id);
            model.addAttribute("memberDTO", memberDTO);
            return request.getRequestURI();
        } catch (MemberIDIsNotExistException e)
        {
            return setError(redirectAttributes, "Member is Not Exist");
        }
    }
    @GetMapping("/edit")
    private String editMember(Model model, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO
            , RedirectAttributes redirectAttributes)
    {
        if(memberSecurityDTO == null) return setError(redirectAttributes,"You are not available this page");
        try {
            MemberDTO memberDTO = memberService.getMemberByID(memberSecurityDTO.getMemberID());
            model.addAttribute("memberDTO", memberDTO);
        } catch (MemberIDIsNotExistException e)
        {
            return setError(redirectAttributes, "Member is Not Exist");
        }
        return "/member/edit";
    }

    private String setError(RedirectAttributes redirectAttributes, String errorMsg)
    {
        redirectAttributes.addFlashAttribute("error", errorMsg);
        return "redirect:/gallery/home";
    }
    @GetMapping("/register")
    public void getMemberRegisterForm()
    {

    }
    @PostMapping("/register")
    public String joinMember(RedirectAttributes redirectAttributes, @Valid MemberDTO memberDTO)
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
    public void login(Model model, HttpServletRequest request)
    {
        request.getSession().setAttribute("prevPage", request.getHeader("Referer"));
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
