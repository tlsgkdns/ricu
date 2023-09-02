package com.shin.ricu.controller;

import com.shin.ricu.domain.Member;
import com.shin.ricu.dto.MemberDTO;
import com.shin.ricu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/memberRest")
public class MemberRestController {

    private final MemberService memberService;
    @GetMapping("/memberID/{memberID}")
    public int isAvailableID(@PathVariable String memberID)
    {
        log.info(memberID + " is " + memberService.isAvailableID(memberID));
        return memberService.isAvailableID(memberID);
    }
    @GetMapping("/nickname/{name}")
    public int isAvailableNickname(@PathVariable String name)
    {
        log.info(name + " is " + memberService.isAvailableNickname(name));
        return memberService.isAvailableNickname(name);
    }
    @GetMapping("/password/{password}/{passwordCheck}")
    public int isPasswordAvailable(@PathVariable String password, @PathVariable String passwordCheck)
    {
        log.info(password + " is " + passwordCheck);
        return memberService.isAvailablePassword(password, passwordCheck);
    }
}
