package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.web.argumentResolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SessionManager sessionManager;

    /*
    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if(memberId == null){
            return "home";
        }

        // 로그인
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
     */

    /*
    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        Member member = (Member) sessionManager.getSession(request);

        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
     */

    /*
    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
     */

    /*
    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
     */

    @GetMapping("/")
    public String homeLoginV4ArgumentResolver(@Login Member member, Model model) {
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
}