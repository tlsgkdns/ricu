package com.shin.ricu.controller;

import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.MemberJoinDTO;
import com.shin.ricu.exception.MemberIDExistException;
import com.shin.ricu.repository.MemberRepository;
import com.shin.ricu.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.AbstractDocument;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PreAuthorize("principal.username == #member.nickname")
    @GetMapping("/memberInfo")
    public void userInfo(Model model, @RequestParam("Member") Member member)
    {
        log.info(member + "is coming!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("Member", member);
    }

    @GetMapping("/register")
    public void getRegister(Model model, @RequestParam(required = false)String error)
    {
        model.addAttribute("error", error);
    }

    @PostMapping("/register")
    public String joinMember(RedirectAttributes redirectAttributes, MemberJoinDTO memberJoinDTO)
    {
        Member member;
        try
        {
            member = memberService.joinMember(memberJoinDTO);
            log.info("hello! " + member);
            redirectAttributes.addAttribute("Member", member);
        } catch (MemberIDExistException e)
        {
            redirectAttributes.addFlashAttribute("error", "Already Exist ID");
           return "redirect:/member/register";
        }
        return "redirect:/member/memberInfo";
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
}
