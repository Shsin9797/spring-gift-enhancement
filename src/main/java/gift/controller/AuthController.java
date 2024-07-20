package gift.controller;

import gift.dto.MemberRequestDto;
import gift.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public String main() {
        return "auth/index";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto.getEmail(), memberRequestDto.getPassword());
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute MemberRequestDto memberRequestDto) throws AuthenticationException {
        if (memberService.login(memberRequestDto.getEmail(), memberRequestDto.getPassword())) {
            return memberService.generateTokenFrom(memberRequestDto.getEmail());
        }
        throw new AuthenticationException("로그인 실패");
    }
}
