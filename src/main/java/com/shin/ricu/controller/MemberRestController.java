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
    public boolean isExistByID(@PathVariable String memberID)
    {
        return memberService.isExistByID(memberID);
    }

    @GetMapping("/nickname/{name}")
    public boolean isExistByNickname(@PathVariable String name)
    {
        log.info(name + " is " + memberService.isExistByNickname(name));
        return memberService.isExistByNickname(name);
    }
}
